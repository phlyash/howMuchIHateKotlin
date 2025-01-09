package com.example.myapplication.features.trains.data

import retrofit2.Response
import retrofit2.http.GET
import com.example.myapplication.features.trains.models.TrainType
import com.example.myapplication.features.trains.models.TrainTypeResponse
import retrofit2.http.Path

interface TrainTypeService {
    @GET("api/traintype/")
    suspend fun trainTypes(): Response<TrainTypeResponse>

    @GET("api/traintype/?id={id}/")
    suspend fun trainType(@Path("id") id: Int): Response<TrainTypeResponse>
}