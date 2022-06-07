package com.example.appmaestro.data.dto

import com.example.appmaestro.domain.model.Maestro
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MaestroResponse(
    val maestri:List<Maestro>?=listOf())
