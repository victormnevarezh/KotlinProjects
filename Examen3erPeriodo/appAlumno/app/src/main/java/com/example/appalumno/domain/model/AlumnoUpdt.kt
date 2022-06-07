package com.example.appalumno.domain.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
class AlumnoUpdt (
    val foto: String,
   val nombre: String,
   val aPaterno: String,
   val aMaterno: String,
   val correo: String,
   val password: String,
   val materias: List<String>,
   val calificaciones1: MutableList<String>,
   val calificaciones2: MutableList<String>,
   val calificaciones3: MutableList<String>) : Parcelable {
}