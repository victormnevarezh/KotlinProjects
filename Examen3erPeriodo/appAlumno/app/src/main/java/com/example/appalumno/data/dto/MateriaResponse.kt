package com.example.appalumno.data.dto

import com.example.appalumno.domain.model.Materia
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter=true)
data class MateriaResponse(val materia: List<Materia>?=listOf())
