package com.example.myapplication.features.wagons.data

import retrofit2.Response
import retrofit2.http.GET
import com.example.myapplication.features.wagons.models.Wagon
import com.example.myapplication.features.wagons.models.WagonResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface WagonService {
    @GET("api/wagons/")
    suspend fun wagons(@Query("page") page: Int, @Query("page_size") pageSize: Int,
                       @Query("name") name: String): Response<WagonResponse>

    @GET("api/wagons/")
    suspend fun wagon(@Query("id") id: Int): Response<WagonResponse>

    @PATCH("api/wagons/{id}/")
    fun updateWagon(@Path("id") id: Int, @Body wagon: Wagon): Call<Wagon>

    @DELETE("api/wagons/{id}/")
    fun deleteWagon(@Path("id") id: Int): Call<Wagon>
}