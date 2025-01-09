package com.example.myapplication.features.locomotives.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.myapplication.features.locomotives.models.Locomotive
import kotlinx.coroutines.flow.Flow

class LocomotivePagingSource (private val service: LocomotiveService) {
    fun getLocomotives(name: String): Flow<PagingData<Locomotive>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { LocomotiveRepositoryImpl(service, name) }
        ).flow
    }
}