package com.example.appmaestro.presentation.ListadoAlumnoMateria

import com.example.appmaestro.core.presentation.BaseViewState
import com.example.appmaestro.domain.model.Alumno

class ListadoAlumnoMateriaViewState : BaseViewState() {
    data class alumnoReceived(val alumno : List<Alumno>) : BaseViewState()
}