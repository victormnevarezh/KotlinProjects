package com.example.appmaestro.data.dto

import androidx.lifecycle.GeneratedAdapter
import com.example.appmaestro.domain.model.Materia
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MateriaResponse(
    val materia:List<Materia>? = listOf()
)
