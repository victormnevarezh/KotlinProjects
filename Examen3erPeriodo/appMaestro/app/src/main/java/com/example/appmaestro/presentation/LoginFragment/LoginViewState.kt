package com.example.appmaestro.presentation.LoginFragment

import com.example.appmaestro.core.presentation.BaseViewState
import com.example.appmaestro.domain.model.Maestro

class LoginViewState : BaseViewState() {
    data class LoginReceived(val maestro:List<Maestro>): BaseViewState()
}