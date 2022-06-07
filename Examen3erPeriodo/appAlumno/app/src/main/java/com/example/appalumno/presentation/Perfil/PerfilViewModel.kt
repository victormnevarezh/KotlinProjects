package com.example.appalumno.presentation.Perfil

import com.example.appalumno.core.exception.Failure
import com.example.appalumno.core.interactor.UseCase
import com.example.appalumno.core.presentation.BaseViewModel
import com.example.appalumno.domain.model.Alumno
import com.example.appalumno.domain.usecase.Logout
import com.example.appalumno.domain.usecase.getAlumnoSP
import com.example.appalumno.domain.usecase.saveAlumnoSP
import com.example.appalumno.domain.usecase.updateAlumno
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class PerfilViewModel @Inject constructor(
    private val updateAlumno : updateAlumno,
    private val getAlumnoSP : getAlumnoSP,
    private val saveAlumnoSP: saveAlumnoSP,
    private val Logout : Logout
): BaseViewModel() {
    fun doGetAlumnoSP(){
        getAlumnoSP(UseCase.None()){
            it.fold(::userNotFound){
                setAlumno(it)
            }
            true
        }
    }

    fun doLogout(){
        Logout(UseCase.None()) {
            it.fold(::handleFailure) {
                if (it) state.value = PerfilViewState.UserNotFound
            }
        }
    }

    private fun setAlumno(alumno: Alumno){
        state.value = PerfilViewState.alumnoReceived(alumno)
    }

    fun doUpdateAlumno(alumno:Alumno){
        updateAlumno(alumno){
            it.fold(::handleFailure) {

            }
            true
        }
    }

    fun doSaveAlumno(alumno:Alumno){
        saveAlumnoSP(alumno){
            it.fold(::handleFailure){

            }
        }
    }

    private fun userNotFound(failure: Failure) {
        state.value = PerfilViewState.UserNotFound
        handleFailure(failure)
    }
}