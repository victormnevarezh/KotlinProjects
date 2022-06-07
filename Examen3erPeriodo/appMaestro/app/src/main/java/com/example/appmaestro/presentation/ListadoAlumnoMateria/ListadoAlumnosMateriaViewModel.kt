package com.example.appmaestro.presentation.ListadoAlumnoMateria

import androidx.lifecycle.ViewModel
import com.example.appmaestro.core.presentation.BaseViewModel
import com.example.appmaestro.domain.model.Materia
import com.example.appmaestro.domain.usecase.getAlumnoByMatricula
import com.example.appmaestro.domain.usecase.getMaestroByMatricula
import com.example.appmaestro.domain.usecase.saveMateriaSP
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class ListadoAlumnosMateriaViewModel @Inject constructor(
    private val getAlumnoByMatricula: getAlumnoByMatricula,
    private val saveMateriaSP: saveMateriaSP
): BaseViewModel() {

    fun doGetAlumnoByMatricula(matricula: String) {
        getAlumnoByMatricula(matricula){
            it.fold(::handleFailure){
                state.value = ListadoAlumnoMateriaViewState.alumnoReceived(
                    it.alumni?:listOf()
                )
                true
            }
        }
    }

    fun doSaveMateriaSP(materia : Materia) {
        saveMateriaSP(materia) {
            it.fold(::handleFailure) {

            }
        }
    }
}