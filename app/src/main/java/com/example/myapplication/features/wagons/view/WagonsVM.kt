package com.example.myapplication.features.wagons.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.features.wagons.data.WagonPagingSource
import com.example.myapplication.features.wagons.models.Wagon
import kotlinx.coroutines.flow.Flow

class WagonsVM (
    private val repository: WagonPagingSource
) : ViewModel() {
    fun getWagons(query: String): Flow<PagingData<Wagon>> {
        return repository.getWagons(query).cachedIn(viewModelScope)
    }
}