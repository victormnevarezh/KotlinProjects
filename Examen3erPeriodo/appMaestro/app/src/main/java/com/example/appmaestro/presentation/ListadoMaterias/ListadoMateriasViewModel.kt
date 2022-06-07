package com.example.appmaestro.presentation.ListadoMaterias

import androidx.lifecycle.ViewModel
import com.example.appmaestro.core.exception.Failure
import com.example.appmaestro.core.interactor.UseCase
import com.example.appmaestro.core.presentation.BaseViewModel
import com.example.appmaestro.domain.model.Maestro
import com.example.appmaestro.domain.usecase.getMaestroByMatricula
import com.example.appmaestro.domain.usecase.getMaestroSP
import com.example.appmaestro.domain.usecase.getMateria
import com.example.appmaestro.presentation.LoginFragment.LoginViewState
import com.example.appmaestro.presentation.Perfil.PerfilViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class ListadoMateriasViewModel @Inject constructor(
    private val getMateria: getMateria,
    private val getMaestroSP: getMaestroSP
) : BaseViewModel() {
    fun doGetMateria(nombre: String) {
        getMateria(nombre){
            it.fold(::handleFailure){
                state.value = ListadoMateriasViewState.materiaReceived(
                    it.materia?: listOf()
                )
                true
            }
        }
    }

    fun doGetMaestro(){
        getMaestroSP(UseCase.None()){
            it.fold(::userNotFound) {
                setMaestro(it)
            }
        }
    }

    private fun userNotFound(failure: Failure) {
        state.value = ListadoMateriasViewState.UserNotFound
        handleFailure(failure)
    }

    fun setMaestro (maestro : Maestro) {
        state.value = ListadoMateriasViewState.maestroReceived(maestro)
    }
}