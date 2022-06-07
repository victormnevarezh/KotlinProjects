package com.example.appalumno.presentation.DetalleCalificaciones

import com.example.appalumno.core.presentation.BaseViewState
import com.example.appalumno.domain.model.Alumno

class DetalleCalificacionesViewState : BaseViewState() {
    data class AlumnoReceived(val alumno: Alumno) : BaseViewState()
}