package com.example.appalumno.presentation.Login

import com.example.appalumno.core.presentation.BaseViewState
import com.example.appalumno.domain.model.Alumno

class LoginViewState : BaseViewState() {
    data class LoginReceived(val alumno:List<Alumno>):BaseViewState()
}