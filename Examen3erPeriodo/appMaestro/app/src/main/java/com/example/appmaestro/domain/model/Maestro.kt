package com.example.appmaestro.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
@JsonClass(generateAdapter = true)
class Maestro(
    @PrimaryKey(autoGenerate = false)
    var matricula: String = "",
    var foto: String = "",
    var nombre: String = "",
    var aPaterno: String = "",
    var aMaterno: String = "",
    var correo: String = "",
    var password: String = "",
    val materias: List<String> = emptyList()
    ) : Parcelable { }