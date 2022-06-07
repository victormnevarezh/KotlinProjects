package com.example.appmaestro.presentation.LoginFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.appmaestro.R
import com.example.appmaestro.core.extension.failure
import com.example.appmaestro.core.extension.observe
import com.example.appmaestro.core.presentation.BaseFragment
import com.example.appmaestro.core.presentation.BaseViewState
import com.example.appmaestro.databinding.LoginFragmentBinding
import com.example.appmaestro.domain.model.Maestro
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@AndroidEntryPoint
@WithFragmentBindings
class LoginFragment : BaseFragment(R.layout.login_fragment) {

    private lateinit var binding: LoginFragmentBinding
    private lateinit var logedMaestro: Maestro

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure,::handleFailure)
        }
    }

    override fun setBinding(view: View) {
        binding = LoginFragmentBinding.bind(view)
        binding.lifecycleOwner = this
        binding.btnLogin.setOnClickListener {
            val mat = binding.edtMatricula.text.toString()
            loginViewModel.doGetMaestroByMatricula(mat)
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when(state){
            is LoginViewState.LoginReceived -> saveMaestro(state.maestro)
        }
    }

    fun saveMaestro(maestro: List<Maestro>){
        if (maestro.size>0){
            logedMaestro = maestro.first()
            if(logedMaestro.password.equals(binding.edtPassword.text.toString())){
                loginViewModel.doSaveMaestro(logedMaestro)
                navController.navigate(LoginFragmentDirections.actionLoginFragmentToListadoMateriasFragment())
            }
        }
    }

}