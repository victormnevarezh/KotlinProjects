package com.example.appalumno.presentation.Perfil

import com.example.appalumno.core.presentation.BaseViewState
import com.example.appalumno.domain.model.Alumno

class PerfilViewState : BaseViewState() {
    data class alumnoReceived(val alumno: Alumno) : BaseViewState()
    object UserNotFound : BaseViewState()
}