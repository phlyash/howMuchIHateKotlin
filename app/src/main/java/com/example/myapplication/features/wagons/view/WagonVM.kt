package com.example.myapplication.features.wagons.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.features.wagons.data.WagonRepository
import com.example.myapplication.features.wagons.models.Wagon
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WagonVM (
    private val repository: WagonRepository
) : ViewModel() {
    private val _wagon = MutableLiveData<Wagon>()
    val wagon: LiveData<Wagon> get() = _wagon

    fun fetchWagon(id: Int) {
        viewModelScope.launch {
            _wagon.value = repository.getWagon(id)
        }
    }

    fun updateWagon(id: Int, wagon: Wagon) {
        repository.updateWagon(id, wagon).enqueue(object : Callback<Wagon> {
            override fun onResponse(call: Call<Wagon>, response: Response<Wagon>) {
                // handle the response
            }

            override fun onFailure(call: Call<Wagon>, t: Throwable) {
                // handle the failure
            }
        })

    }

    fun deleteWagon(id: Int) {
        repository.deleteWagon(id).enqueue(object : Callback<Wagon> {
            override fun onResponse(call: Call<Wagon>, response: Response<Wagon>) {
                // handle the response
            }

            override fun onFailure(call: Call<Wagon>, t: Throwable) {
                // handle the failure
            }
        })
    }
}