package com.example.myapplication.features.wagons.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.myapplication.features.wagons.models.Wagon
import kotlinx.coroutines.flow.Flow

class WagonPagingSource(private val service: WagonService) {
    fun getWagons(name: String): Flow<PagingData<Wagon>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { WagonRepositoryImpl(service, name) }
        ).flow
    }
}