package com.example.myapplication.features.wagons.data

import com.example.myapplication.features.wagons.models.WagonType

interface WagonTypeRepository {
    suspend fun getWagonTypes(): List<WagonType>
}

class WagonTypeRepositoryImpl(private val service: WagonTypeService): WagonTypeRepository {
    override suspend fun getWagonTypes(): List<WagonType> {
        val body = service.getWagonTypes().body()!!
        return body.results.orEmpty()
    }
}