package com.example.appmaestro.presentation.LoginFragment

import androidx.lifecycle.ViewModel
import com.example.appmaestro.core.presentation.BaseViewModel
import com.example.appmaestro.domain.model.Maestro
import com.example.appmaestro.domain.usecase.getMaestroByMatricula
import com.example.appmaestro.domain.usecase.saveMaestroSP
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getMaestroByMatricula: getMaestroByMatricula,
    private val saveMaestro: saveMaestroSP
): BaseViewModel() {
    fun doGetMaestroByMatricula(matricula: String) {
        getMaestroByMatricula(matricula){
            it.fold(::handleFailure){
                state.value = LoginViewState.LoginReceived(
                    it.maestri?:listOf()
                )
                true
            }
        }
    }

    fun doSaveMaestro(maestro: Maestro){
        saveMaestro(maestro){
            it.fold(::handleFailure) {

            }
        }
    }
}