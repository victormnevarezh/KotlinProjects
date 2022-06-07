package com.example.appmaestro.data.api

import com.example.appmaestro.data.dto.AlumnoResponse
import com.example.appmaestro.domain.model.Alumno
import com.example.appmaestro.domain.model.AlumnoUpdt
import retrofit2.Call
import retrofit2.http.*

interface AlumnoApi {
    @GET("/prod/api/alumni/{matricula}")
    fun getAlumnoByMatricula(@Path("matricula") matricula: String): Call<AlumnoResponse>

    @PUT("/prod/api/alumni/{matricula}")
    fun updateAlumno(@Path("matricula") matricula: String, @Body alumno: AlumnoUpdt): Call<AlumnoResponse>
}
