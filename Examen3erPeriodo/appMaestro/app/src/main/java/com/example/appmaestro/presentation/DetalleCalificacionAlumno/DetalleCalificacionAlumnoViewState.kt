package com.example.appmaestro.presentation.DetalleCalificacionAlumno

import com.example.appmaestro.core.presentation.BaseViewState
import com.example.appmaestro.domain.model.Materia
import com.example.appmaestro.domain.usecase.getMaestroByMatricula
import javax.inject.Inject

class DetalleCalificacionAlumnoViewState : BaseViewState() {
    data class materiaReceived (val materia : Materia) : BaseViewState()
}