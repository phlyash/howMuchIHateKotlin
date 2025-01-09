package com.example.myapplication.features.statistics.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.features.statistics.data.StatisticsRepository
import com.example.myapplication.features.statistics.models.Statistics
import kotlinx.coroutines.launch

class StatisticsVM (
    private val repository: StatisticsRepository
) : ViewModel() {
    private val _statistics = MutableLiveData<Statistics>()
    val statistics: LiveData<Statistics> get() = _statistics

    fun fetchStatistics() {
        viewModelScope.launch {
            _statistics.value = repository.statistics()
        }
    }
}