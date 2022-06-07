package com.example.appmaestro.presentation.Perfil


import com.example.appmaestro.core.exception.Failure
import com.example.appmaestro.core.interactor.UseCase
import com.example.appmaestro.core.presentation.BaseViewModel
import com.example.appmaestro.domain.model.Maestro
import com.example.appmaestro.domain.model.Materia
import com.example.appmaestro.domain.usecase.Logout
import com.example.appmaestro.domain.usecase.getMaestroSP
import com.example.appmaestro.domain.usecase.saveMaestroSP
import com.example.appmaestro.domain.usecase.updateMaestro
import com.example.appmaestro.presentation.DetalleCalificacionAlumno.DetalleCalificacionAlumnoViewState
import com.example.appmaestro.presentation.ListadoMaterias.ListadoMateriasViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class PerfilViewModel @Inject constructor(
    private val updateMaestro: updateMaestro,
    private val getMaestroSP: getMaestroSP,
    private val saveMaestro: saveMaestroSP,
    private val Logout : Logout
    ) : BaseViewModel() {
    fun doUpdateMaestro(maestro: Maestro){
        updateMaestro(maestro){
            it.fold(::handleFailure){

            }
            true
        }
    }

    fun doGetMaestro(){
        getMaestroSP(UseCase.None()){
            it.fold(::userNotFound) {
                setMaestro(it)
            }
        }
    }

    fun setMaestro (maestro : Maestro) {
        state.value = PerfilViewState.maestroReceived(maestro)
    }

    fun doSaveMaestro(maestro: Maestro){
        saveMaestro(maestro){
            it.fold(::handleFailure) {

            }
        }
    }

    private fun userNotFound(failure: Failure) {
        state.value = PerfilViewState.UserNotFound
        handleFailure(failure)
    }

    fun doLogout(){
        Logout(UseCase.None()) {
            it.fold(::handleFailure) {
                if (it) state.value = PerfilViewState.UserNotFound
            }
        }
    }

}