package com.example.appmaestro.presentation.ListadoMaterias

import com.example.appmaestro.core.presentation.BaseViewState
import com.example.appmaestro.domain.model.Maestro
import com.example.appmaestro.domain.model.Materia

class ListadoMateriasViewState : BaseViewState() {
    data class materiaReceived (val materia : List<Materia>) : BaseViewState()
    data class maestroReceived (val maestro : Maestro) : BaseViewState()
    object UserNotFound : BaseViewState()
}