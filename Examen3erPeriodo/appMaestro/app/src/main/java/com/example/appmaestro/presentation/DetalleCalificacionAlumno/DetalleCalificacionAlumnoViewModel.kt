package com.example.appmaestro.presentation.DetalleCalificacionAlumno

import androidx.lifecycle.ViewModel
import com.example.appmaestro.core.interactor.UseCase
import com.example.appmaestro.core.presentation.BaseViewModel
import com.example.appmaestro.domain.model.Alumno
import com.example.appmaestro.domain.model.Materia
import com.example.appmaestro.domain.usecase.getAlumnoByMatricula
import com.example.appmaestro.domain.usecase.getMateriaSP
import com.example.appmaestro.domain.usecase.updateAlumno
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class DetalleCalificacionAlumnoViewModel @Inject constructor(
    private val updateAlumno: updateAlumno,
    private val getMateriaSP: getMateriaSP
): BaseViewModel() {

    fun doUpdateAlumno(alumno: Alumno) {
        updateAlumno(alumno) {
            it.fold(::handleFailure) {
            }
            true
        }
    }



    fun doGetMateriaSP () {
        getMateriaSP(UseCase.None()) {
            it.fold(::handleFailure) {
                setMateria(it)
            }
        }
    }
    fun setMateria (materia : Materia) {
        state.value = DetalleCalificacionAlumnoViewState.materiaReceived(materia)
    }

}