package com.example.myapplication.features.trains.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.myapplication.features.trains.models.Train
import kotlinx.coroutines.flow.Flow


class TrainPagingSource(private val service: TrainService) {
    fun getTrains(name: String): Flow<PagingData<Train>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TrainRepositoryImpl(service, name) }
        ).flow
    }
}