package com.example.myapplication.features.trains.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.features.trains.data.TrainTypeRepository
import com.example.myapplication.features.trains.models.TrainType
import kotlinx.coroutines.launch

class TrainTypesVM (
    private val repository: TrainTypeRepository
) : ViewModel() {
    private val _train_types = MutableLiveData<List<TrainType>>()
    val train_types: LiveData<List<TrainType>> get() = _train_types

    fun fetchTrainTypes() {
        viewModelScope.launch {
            _train_types.value = repository.getTrainTypes()
        }
    }
}