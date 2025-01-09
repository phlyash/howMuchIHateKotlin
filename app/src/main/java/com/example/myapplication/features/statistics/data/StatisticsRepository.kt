package com.example.myapplication.features.statistics.data

import com.example.myapplication.features.statistics.models.Statistics


interface StatisticsRepository {
    suspend fun statistics(): Statistics
}

class StatisticsRepositoryImpl(private val service: StatisticsService) : StatisticsRepository {
    override suspend fun statistics(): Statistics {
        val body = service.statistics().body()!!
        return body
    }
}