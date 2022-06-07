package com.example.appalumno.presentation.ListadoMaterias

import com.example.appalumno.core.exception.Failure
import com.example.appalumno.core.interactor.UseCase
import com.example.appalumno.core.presentation.BaseViewModel
import com.example.appalumno.domain.model.Alumno
import com.example.appalumno.domain.usecase.getAlumnoSP
import com.example.appalumno.domain.usecase.getMateria
import com.example.appalumno.presentation.Perfil.PerfilViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject


@DelicateCoroutinesApi
@HiltViewModel
class ListadoMateriasViewModel @Inject constructor(
    private val getMateria : getMateria,
    private val getAlumnoSP: getAlumnoSP,
):
    BaseViewModel()  {
    fun doGetMateria(nombre:String){
        getMateria(nombre){
            it.fold(::handleFailure){
                state.value = ListadoMateriasViewState.MateriaReceived(
                    it.materia?:listOf()
                )
                true
            }
        }
    }

    fun doGetAlumnoSP(){
        getAlumnoSP(UseCase.None()){
            it.fold(::userNotFound){
                setAlumno(it)
            }
            true
        }
    }
    private fun userNotFound(failure: Failure) {
        state.value = ListadoMateriasViewState.UserNotFound
        handleFailure(failure)
    }

    private fun setAlumno(alumno: Alumno){
        state.value = ListadoMateriasViewState.AlumnoReceived(alumno)
    }
}