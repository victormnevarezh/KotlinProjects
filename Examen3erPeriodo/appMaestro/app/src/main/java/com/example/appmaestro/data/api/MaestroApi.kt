package com.example.appmaestro.data.api

import com.example.appmaestro.data.dto.MaestroResponse
import com.example.appmaestro.domain.model.MaestroUpdt
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface MaestroApi {
    @GET("/prod/api/maestri/{matricula}")
    fun getMaestroByMatricula(@Path("matricula") matricula: String): Call<MaestroResponse>

    @PUT("/prod/api/maestri/{matricula}")
    fun updateMaestro(@Path("matricula") matricula: String, @Body maestro: MaestroUpdt): Call<MaestroResponse>
}
