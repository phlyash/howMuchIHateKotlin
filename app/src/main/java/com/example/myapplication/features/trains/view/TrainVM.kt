package com.example.myapplication.features.trains.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.features.trains.data.TrainRepository
import com.example.myapplication.features.trains.models.Train
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrainVM (
    private val repository: TrainRepository
) : ViewModel() {
    private val _train = MutableLiveData<Train>()
    val train: LiveData<Train> get() = _train

    fun fetchTrain(id: Int) {
        viewModelScope.launch {
            _train.value = repository.getTrain(id)
        }
    }

    fun updateTrain(id: Int, train: Train) {
            repository.updateTrain(id, train).enqueue(object : Callback<Train> {
                override fun onResponse(call: Call<Train>, response: Response<Train>) {
                }

                override fun onFailure(call: Call<Train>, t: Throwable) {
                }
            })

    }

    fun createTrain(train: Train) {
        repository.createTrain(train).enqueue(object : Callback<Train> {
            override fun onResponse(call: Call<Train>, response: Response<Train>) {
            }

            override fun onFailure(call: Call<Train>, t: Throwable) {
            }
        })
    }

    fun deleteTrain(id: Int) {
        repository.deleteTrain(id).enqueue(object : Callback<Train> {
            override fun onResponse(call: Call<Train>, response: Response<Train>) {
                // handle the response
            }

            override fun onFailure(call: Call<Train>, t: Throwable) {
                // handle the failure
            }
        })
    }
}