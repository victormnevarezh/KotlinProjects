package com.example.appmaestro.domain.model

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
    val matricula: String,
    val foto: String,
    val nombre: String,
    val aPaterno: String,
    val aMaterno: String,
    val correo: String,
    val password: String,
    val materias: List<String>,
    val calificaciones1: MutableList<String>,
    val calificaciones2: MutableList<String>,
    val calificaciones3: MutableList<String>
) : Parcelable{}