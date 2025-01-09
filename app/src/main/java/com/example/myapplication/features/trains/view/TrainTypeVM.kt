package com.example.myapplication.features.trains.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.features.trains.data.TrainTypeRepository
import com.example.myapplication.features.trains.models.TrainType
import kotlinx.coroutines.launch

class TrainTypeVM (
    private val repository: TrainTypeRepository
) : ViewModel() {
    private val _train_type = MutableLiveData<TrainType>()
    val train_type: LiveData<TrainType> get() = _train_type

    fun fetchTrainTypes(id: Int) {
        viewModelScope.launch {
            _train_type.value = repository.getTrainType(id)
        }
    }
}