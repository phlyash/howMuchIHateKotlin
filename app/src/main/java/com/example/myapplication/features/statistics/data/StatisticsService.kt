package com.example.myapplication.features.statistics.data

import com.example.myapplication.features.statistics.models.Statistics
import retrofit2.Response
import retrofit2.http.GET

interface StatisticsService {
    @GET("api/statistics/")
    suspend fun statistics(): Response<Statistics>
}