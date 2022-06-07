package com.example.appmaestro.presentation.Perfil

import com.example.appmaestro.core.presentation.BaseViewState
import com.example.appmaestro.domain.model.Maestro

class PerfilViewState : BaseViewState() {
    data class maestroReceived(val maestro: Maestro) :BaseViewState()
    object UserNotFound : BaseViewState()
}