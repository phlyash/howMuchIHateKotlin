package com.example.myapplication.features.trains.view

import com.example.myapplication.features.trains.data.TrainPagingSource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.features.trains.models.Train
import kotlinx.coroutines.flow.Flow

class TrainsVM (
    private val repository: TrainPagingSource
) : ViewModel() {
    fun getTrains(query: String): Flow<PagingData<Train>> {
        return repository.getTrains(query).cachedIn(viewModelScope)
    }
}