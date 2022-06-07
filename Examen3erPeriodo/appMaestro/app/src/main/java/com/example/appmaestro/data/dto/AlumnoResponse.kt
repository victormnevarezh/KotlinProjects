package com.example.appmaestro.data.dto

import com.example.appmaestro.domain.model.Alumno
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AlumnoResponse(
    val alumni:List<Alumno>? = listOf()
)
