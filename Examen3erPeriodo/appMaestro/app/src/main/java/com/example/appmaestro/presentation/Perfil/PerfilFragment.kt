package com.example.appmaestro.presentation.Perfil

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import com.example.appmaestro.R
import com.example.appmaestro.core.extension.failure
import com.example.appmaestro.core.extension.loadFromURL
import com.example.appmaestro.core.extension.observe
import com.example.appmaestro.core.presentation.BaseFragment
import com.example.appmaestro.core.presentation.BaseViewState
import com.example.appmaestro.databinding.DetalleCalificacionAlumnoFragmentBinding
import com.example.appmaestro.databinding.PerfilFragmentBinding
import com.example.appmaestro.domain.model.Maestro
import com.example.appmaestro.domain.model.Materia
import com.example.appmaestro.presentation.DetalleCalificacionAlumno.DetalleCalificacionAlumnoViewState
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@AndroidEntryPoint
@WithFragmentBindings
class PerfilFragment : BaseFragment(R.layout.perfil_fragment) {

    private lateinit var binding : PerfilFragmentBinding
    private val PerfilViewModel by viewModels<PerfilViewModel>()
    private var maestro = Maestro()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PerfilViewModel.apply{
            observe(state, ::onViewStateChanged)
            failure(failure,::handleFailure)
            doGetMaestro()
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is PerfilViewState.maestroReceived -> setMaestro(state.maestro)
            is PerfilViewState.UserNotFound -> navController.navigate(PerfilFragmentDirections.actionPerfilFragmentToLoginFragment())
        }
    }

    fun setMaestro(maestro : Maestro) {
        this.maestro = maestro
        binding.item = maestro

        binding.imgMaestro.loadFromURL(maestro.foto)
    }

    override fun setBinding(view: View) {
        binding = PerfilFragmentBinding.bind(view)
        binding.lifecycleOwner = this

        binding.btnAceptar.setOnClickListener{
            maestro.nombre = binding.edtNombre.text.toString()
            maestro.aPaterno = binding.edtApellidoPaterno.text.toString()
            maestro.aMaterno = binding.edtApellidoMaterno.text.toString()
            maestro.correo = binding.edtCorreo.text.toString()
            maestro.foto = binding.edtImg.text.toString()

            PerfilViewModel.doUpdateMaestro(maestro)
            PerfilViewModel.doSaveMaestro(maestro)
        }


        binding.btnLogout.setOnClickListener{
            PerfilViewModel.doLogout()
        }
    }
}