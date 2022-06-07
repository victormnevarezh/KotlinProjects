package com.example.appalumno.presentation.Perfil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.appalumno.R
import com.example.appalumno.core.extension.failure
import com.example.appalumno.core.extension.loadFromURL
import com.example.appalumno.core.extension.observe
import com.example.appalumno.core.presentation.BaseFragment
import com.example.appalumno.core.presentation.BaseViewState
import com.example.appalumno.databinding.FragmentPerfilBinding
import com.example.appalumno.domain.model.Alumno
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@AndroidEntryPoint
@WithFragmentBindings
class PerfilFragment : BaseFragment(R.layout.fragment_perfil) {

    private lateinit var binding : FragmentPerfilBinding
    private val PerfilViewModel by viewModels<PerfilViewModel>()
    private var alumno = Alumno()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PerfilViewModel.apply{
            observe(state, ::onViewStateChanged)
            failure(failure,::handleFailure)
            doGetAlumnoSP()
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is PerfilViewState.alumnoReceived -> setMaestro(state.alumno)
            is PerfilViewState.UserNotFound -> navController.navigate(PerfilFragmentDirections.actionPerfilFragmentToLoginFragment())
        }
    }

    fun setMaestro(alumno : Alumno) {
        this.alumno = alumno
        binding.item = alumno

        binding.imgAlumno.loadFromURL(alumno.foto)
    }

    override fun setBinding(view: View) {
        binding = FragmentPerfilBinding.bind(view)
        binding.lifecycleOwner = this

        binding.btnAceptar.setOnClickListener{
            alumno.nombre = binding.edtNombre.text.toString()
            alumno.aPaterno = binding.edtApellidoPaterno.text.toString()
            alumno.aMaterno = binding.edtApellidoMaterno.text.toString()
            alumno.correo = binding.edtCorreo.text.toString()
            alumno.foto = binding.edtImg.text.toString()

            PerfilViewModel.doUpdateAlumno(alumno)
            PerfilViewModel.doSaveAlumno(alumno)
        }

        binding.btnLogout.setOnClickListener{
            PerfilViewModel.doLogout()
        }
    }

}