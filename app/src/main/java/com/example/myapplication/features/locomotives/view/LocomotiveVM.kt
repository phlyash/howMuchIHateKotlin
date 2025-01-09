package com.example.myapplication.features.locomotives.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.features.locomotives.models.Locomotive
import com.example.myapplication.features.locomotives.data.LocomotiveRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocomotiveVM (
    private val repository: LocomotiveRepository
) : ViewModel() {
    private val _locomotive = MutableLiveData<Locomotive>()
    val locomotive: LiveData<Locomotive> get() = _locomotive

    fun fetchLocomotive(id: Int) {
        viewModelScope.launch {
            _locomotive.value = repository.getLocomotive(id)
        }
    }

    fun createLocomotive(locomotive: Locomotive) {
        repository.createLocomotive(locomotive).enqueue(object : Callback<Locomotive> {
            override fun onResponse(call: Call<Locomotive>, response: Response<Locomotive>) {
                // handle the response
            }

            override fun onFailure(call: Call<Locomotive>, t: Throwable) {
                // handle the failure
            }
        })
    }

    fun updateLocomotive(id: Int, locomotive: Locomotive) {
        repository.updateLocomotive(id, locomotive).enqueue(object : Callback<Locomotive> {
            override fun onResponse(call: Call<Locomotive>, response: Response<Locomotive>) {
                // handle the response
            }

            override fun onFailure(call: Call<Locomotive>, t: Throwable) {
                // handle the failure
            }
        })

    }

    fun deleteLocomotive(id: Int) {
        repository.deleteLocomotive(id).enqueue(object : Callback<Locomotive> {
            override fun onResponse(call: Call<Locomotive>, response: Response<Locomotive>) {
                // handle the response
            }

            override fun onFailure(call: Call<Locomotive>, t: Throwable) {
                // handle the failure
            }
        })
    }
}