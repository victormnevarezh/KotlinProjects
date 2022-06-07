package com.example.appmaestro.data.api

import com.example.appmaestro.data.dto.MaestroResponse
import com.example.appmaestro.data.dto.MateriaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MateriaApi {
    @GET("/prod/api/materia/{nombre}")
    fun getMateria(@Path("nombre") nombre: String): Call<MateriaResponse>
}