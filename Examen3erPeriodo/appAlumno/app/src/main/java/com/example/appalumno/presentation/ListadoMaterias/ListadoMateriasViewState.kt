package com.example.appalumno.presentation.ListadoMaterias

import com.example.appalumno.core.presentation.BaseViewState
import com.example.appalumno.domain.model.Alumno
import com.example.appalumno.domain.model.Materia

class ListadoMateriasViewState : BaseViewState() {
    data class MateriaReceived(val materia : List<Materia>) : BaseViewState()
    data class AlumnoReceived(val alumno: Alumno) : BaseViewState()
    object UserNotFound : BaseViewState()
}