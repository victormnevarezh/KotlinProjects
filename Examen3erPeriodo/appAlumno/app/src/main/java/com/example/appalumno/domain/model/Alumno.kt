package com.example.appalumno.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
@JsonClass(generateAdapter = true)
class Alumno(
    @PrimaryKey(autoGenerate = false)
    val matricula: String = "",
    var foto: String = "",
    var nombre: String = "",
    var aPaterno: String = "",
    var aMaterno: String = "",
    var correo: String = "",
    var password: String = "",
    val materias: List<String> = emptyList(),
    val calificaciones1: MutableList<String> = ArrayList(),
    val calificaciones2: MutableList<String> =ArrayList(),
   val calificaciones3: MutableList<String> = ArrayList(),
) : Parcelable{}