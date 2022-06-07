package com.example.appmaestro.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
class MaestroUpdt (
    val foto: String,
    val nombre: String,
    val aPaterno: String,
    val aMaterno: String,
    val correo: String,
    val password: String,
    val materias: List<String>
) : Parcelable { }
