package com.example.appalumno.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
@JsonClass(generateAdapter = true)
class Materia(
    @PrimaryKey(autoGenerate = false)
    val nombre : String,
    val idMateria : String,
    val descripcion:String,
    val foto : String,
    val dias : List<String>,
    val horas : List<String>,
    val matriculados : List<String>
) : Parcelable { }