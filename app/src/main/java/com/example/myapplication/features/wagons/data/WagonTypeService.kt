package com.example.myapplication.features.wagons.data

import retrofit2.Response
import retrofit2.http.GET
import com.example.myapplication.features.wagons.models.WagonTypeResponse

interface WagonTypeService {
    @GET("api/wagontype/")
    suspend fun getWagonTypes(): Response<WagonTypeResponse>
}