package com.example.appalumno.presentation.DetalleCalificaciones

import androidx.lifecycle.ViewModel
import com.example.appalumno.core.interactor.UseCase
import com.example.appalumno.core.presentation.BaseViewModel
import com.example.appalumno.domain.model.Alumno
import com.example.appalumno.domain.usecase.getAlumnoSP
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class DetalleCalificacionesViewModel @Inject constructor(
    private val getAlumnoSP: getAlumnoSP
): BaseViewModel() {
   fun doGetAlumnoSP(){
       getAlumnoSP(UseCase.None()){
           it.fold(::handleFailure){
               setAlumno(it)
           }
           true
       }
   }

   private fun setAlumno(alumno: Alumno){
       state.value = DetalleCalificacionesViewState.AlumnoReceived(alumno)
   }
}