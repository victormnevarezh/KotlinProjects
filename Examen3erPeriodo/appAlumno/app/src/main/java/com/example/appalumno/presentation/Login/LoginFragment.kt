package com.example.appalumno.presentation.Login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.appalumno.R
import com.example.appalumno.core.extension.failure
import com.example.appalumno.core.extension.observe
import com.example.appalumno.core.platform.AuthManager
import com.example.appalumno.core.presentation.BaseFragment
import com.example.appalumno.core.presentation.BaseViewState
import com.example.appalumno.databinding.FragmentLoginBinding
import com.example.appalumno.domain.model.Alumno
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@AndroidEntryPoint
@WithFragmentBindings
class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private lateinit var binding : FragmentLoginBinding
    private lateinit var logedAlumno : Alumno

    private val loginViewModel by viewModels<LoginViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure,::handleFailure)
        }

    }

    override fun setBinding(view: View) {
        binding = FragmentLoginBinding.bind(view)
        binding.lifecycleOwner = this
        binding.btnLogin.setOnClickListener {
            val mat = binding.edtMatricula.text.toString()
            loginViewModel.doGetAlumnoByMatricula(mat)
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when(state){
            is LoginViewState.LoginReceived -> saveAlumno(state.alumno)
        }
    }

    fun saveAlumno(alumno: List<Alumno>){
        if (alumno.size>0){
            logedAlumno = alumno.first()
            loginViewModel.doSaveAlumno(logedAlumno)
            if(logedAlumno.password.equals(binding.edtPassword.text.toString())){
                navController.navigate(LoginFragmentDirections.actionLoginFragmentToListadoMateriasFragment())
            }
        }
    }
}