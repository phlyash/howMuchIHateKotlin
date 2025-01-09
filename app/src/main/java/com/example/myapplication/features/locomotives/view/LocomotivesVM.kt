package com.example.myapplication.features.locomotives.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.features.locomotives.data.LocomotivePagingSource
import com.example.myapplication.features.locomotives.models.Locomotive
import kotlinx.coroutines.flow.Flow

class LocomotivesVM (
    private val repository: LocomotivePagingSource
) : ViewModel() {
    fun getLocomotives(query: String): Flow<PagingData<Locomotive>> {
        return repository.getLocomotives(query).cachedIn(viewModelScope)
    }
}