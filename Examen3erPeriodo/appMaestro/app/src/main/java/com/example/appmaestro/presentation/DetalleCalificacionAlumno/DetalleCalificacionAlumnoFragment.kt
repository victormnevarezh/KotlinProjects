package com.example.appmaestro.presentation.DetalleCalificacionAlumno

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.appmaestro.R
import com.example.appmaestro.core.extension.failure
import com.example.appmaestro.core.extension.observe
import com.example.appmaestro.core.presentation.BaseFragment
import com.example.appmaestro.core.presentation.BaseViewState
import com.example.appmaestro.databinding.DetalleCalificacionAlumnoFragmentBinding
import com.example.appmaestro.domain.model.Alumno
import com.example.appmaestro.domain.model.Materia
import com.example.appmaestro.domain.usecase.getMateriaSP
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@AndroidEntryPoint
@WithFragmentBindings
class DetalleCalificacionAlumnoFragment : BaseFragment(R.layout.detalle_calificacion_alumno_fragment) {

    private lateinit var binding : DetalleCalificacionAlumnoFragmentBinding
    private val args : DetalleCalificacionAlumnoFragmentArgs by navArgs()

    private var materia = Materia()
    private var index : Int = 0
    private val DetalleCalificacionAlumnoViewModel by viewModels<DetalleCalificacionAlumnoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetalleCalificacionAlumnoViewModel.apply{
            observe(state, ::onViewStateChanged)
            failure(failure,::handleFailure)
            doGetMateriaSP()
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is DetalleCalificacionAlumnoViewState.materiaReceived -> setAlumno(state.materia)
        }
    }

    override fun setBinding(view: View) {
        binding = DetalleCalificacionAlumnoFragmentBinding.bind(view)
        binding.lifecycleOwner = this
        binding.btnAceptarCalificacion.setOnClickListener {
            val alumno = args.alumno

            val calificaciones1 = binding.edtPrimerPeriodo.text.toString()
            val calificaciones2 = binding.edtSegundoPeriodo.text.toString()
            val calificaciones3 = binding.edtTercerPeriodo.text.toString()
            alumno.calificaciones1.set(index, calificaciones1)
            alumno.calificaciones2.set(index, calificaciones2)
            alumno.calificaciones3.set(index, calificaciones3)

            DetalleCalificacionAlumnoViewModel.doUpdateAlumno(alumno)

            navController.navigate(DetalleCalificacionAlumnoFragmentDirections.actionDetalleCalificacionAlumnoFragmentToListadoAlumnosMateriaFragment(materia))
        }
    }

    fun setAlumno(materia : Materia) {
        this.materia = materia
        val alumno = args.alumno
        index = alumno.materias.indexOf(materia.nombre)
        binding.txvNombre.setText(alumno.nombre)
        binding.txvAPaterno.setText(alumno.aPaterno)
        binding.txvAMaterno.setText(alumno.aMaterno)

        binding.edtPrimerPeriodo.setText(alumno.calificaciones1.get(index))
        binding.edtSegundoPeriodo.setText(alumno.calificaciones2.get(index))
        binding.edtTercerPeriodo.setText(alumno.calificaciones3.get(index))

    }
}