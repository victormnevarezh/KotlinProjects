package com.example.appalumno.presentation.DetalleCalificaciones

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.appalumno.R
import com.example.appalumno.core.extension.failure
import com.example.appalumno.core.extension.observe
import com.example.appalumno.core.presentation.BaseFragment
import com.example.appalumno.core.presentation.BaseViewState
import com.example.appalumno.databinding.FragmentDetalleCalificacionesBinding
import com.example.appalumno.domain.model.Alumno
import com.example.appalumno.presentation.DetalleMateria.DetalleMateriaFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@AndroidEntryPoint
@WithFragmentBindings
class DetalleCalificacionesFragment : BaseFragment(R.layout.fragment_detalle_calificaciones) {

    private lateinit var binding : FragmentDetalleCalificacionesBinding
    private val args : DetalleCalificacionesFragmentArgs by navArgs()


    private val DetalleCalificacionesViewModel by viewModels<DetalleCalificacionesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetalleCalificacionesViewModel.apply{
            observe(state, ::onViewStateChanged)
            failure(failure,::handleFailure)
            doGetAlumnoSP()
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when(state){
            is DetalleCalificacionesViewState.AlumnoReceived -> workAlumno(state.alumno)
        }
    }

    fun workAlumno(alumno:Alumno){
        if (alumno.matricula!="") {
            val index = alumno.materias.indexOf(args.materia.nombre)
            binding.CalParcial1.setText(alumno.calificaciones1.get(index))
            binding.CalParcial2.setText(alumno.calificaciones2.get(index))
            binding.CalParcial3.setText(alumno.calificaciones3.get(index))
        }
    }

    override fun setBinding(view: View) {
        binding = FragmentDetalleCalificacionesBinding.bind(view)
        binding.lifecycleOwner = this
    }


}