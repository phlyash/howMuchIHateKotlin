package com.example.myapplication.features.trains.data

import retrofit2.Response
import retrofit2.http.GET
import com.example.myapplication.features.trains.models.Train
import com.example.myapplication.features.trains.models.TrainResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TrainService {
    @GET("api/trains/")
    suspend fun trains(@Query("page") page: Int, @Query("page_size") pageSize: Int,
                       @Query("name") name: String): Response<TrainResponse>

    @GET("api/trains/")
    suspend fun train(@Query("id") id: Int): Response<TrainResponse>

    @PATCH("api/trains/{id}/")
    fun updateTrain(@Path("id") id: Int, @Body train: Train): Call<Train>

    @DELETE("api/trains/{id}/")
    fun deleteTrain(@Path("id") id: Int): Call<Train>

    @POST("api/trains/")
    fun createTrain(@Body train: Train): Call<Train>
}