package com.example.myapplication.features.trains.data

import com.example.myapplication.features.trains.models.TrainType

interface TrainTypeRepository {
    suspend fun getTrainTypes(): List<TrainType>
    suspend fun getTrainType(id: Int): TrainType
}

class TrainTypeRepositoryImpl(private val service: TrainTypeService): TrainTypeRepository {
    override suspend fun getTrainTypes(): List<TrainType> {
        val body = service.trainTypes().body()!!
        return body.results.orEmpty()
    }

    override suspend fun getTrainType(id: Int): TrainType {
        val body = service.trainType(id).body()!!
        return body.results.orEmpty()[0]
    }
}