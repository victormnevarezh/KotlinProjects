package com.example.appalumno.presentation.Login

import com.example.appalumno.core.platform.AuthManager
import com.example.appalumno.core.presentation.BaseViewModel
import com.example.appalumno.domain.model.Alumno
import com.example.appalumno.domain.usecase.getAlumnoByMatricula
import com.example.appalumno.domain.usecase.saveAlumnoSP
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getAlumnoByMatricula: getAlumnoByMatricula,
    private val saveAlumnoSP: saveAlumnoSP,
): BaseViewModel() {
    fun doGetAlumnoByMatricula(matricula: String) {
        getAlumnoByMatricula(matricula){
            it.fold(::handleFailure){
                state.value = LoginViewState.LoginReceived(
                    it.alumni?:listOf()
                )
                true
            }
        }
    }

    fun doSaveAlumno(alumno:Alumno){
        saveAlumnoSP(alumno){
            it.fold(::handleFailure){

            }
        }
    }
}