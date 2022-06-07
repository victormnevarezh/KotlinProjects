package com.example.appalumno.data.dto

import com.example.appalumno.domain.model.Alumno
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AlumnoResponse(val alumni:List<Alumno>?=listOf())
