package com.example.myapplication.features.wagons.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.features.wagons.data.WagonTypeRepository
import com.example.myapplication.features.wagons.models.WagonType
import kotlinx.coroutines.launch

class WagonTypeVM(private val repository: WagonTypeRepository) : ViewModel() {
    private val _wagon_types = MutableLiveData<List<WagonType>>()
    val wagon_types: LiveData<List<WagonType>> get() = _wagon_types

    fun fetchTrainTypes() {
        viewModelScope.launch {
            _wagon_types.value = repository.getWagonTypes()
        }
    }
}