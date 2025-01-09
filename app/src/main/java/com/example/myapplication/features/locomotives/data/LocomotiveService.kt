package com.example.myapplication.features.locomotives.data

import retrofit2.Response
import retrofit2.http.GET
import com.example.myapplication.features.locomotives.models.Locomotive
import com.example.myapplication.features.locomotives.models.LocomotiveResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LocomotiveService {
    @GET("api/locomotives/")
    suspend fun locomotives(@Query("page") page: Int, @Query("page_size") pageSize: Int,
                            @Query("name") name: String): Response<LocomotiveResponse>

    @GET("api/locomotives/")
    suspend fun locomotive(@Query("id") id: Int): Response<LocomotiveResponse>

    @PATCH("api/locomotives/{id}/")
    fun updateLocomotive(@Path("id") id: Int, @Body locomotive: Locomotive): Call<Locomotive>

    @DELETE("api/locomotives/{id}/")
    fun deleteLocomotive(@Path("id") id: Int): Call<Locomotive>

    @POST("api/locomotives/")
    fun createLocomotive(@Body locomotive: Locomotive): Call<Locomotive>
}