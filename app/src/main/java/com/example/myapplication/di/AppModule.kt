package com.example.myapplication.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import com.example.myapplication.features.trains.view.TrainsVM
import com.example.myapplication.features.trains.view.TrainVM
import com.example.myapplication.features.trains.view.TrainTypesVM
import com.example.myapplication.features.locomotives.view.LocomotivesVM
import com.example.myapplication.features.locomotives.view.LocomotiveVM
import com.example.myapplication.features.wagons.view.WagonsVM
import com.example.myapplication.features.wagons.view.WagonVM
import com.example.myapplication.features.wagons.view.WagonTypeVM
import com.example.myapplication.features.statistics.view.StatisticsVM


val appModule = module {
    viewModelOf(::TrainsVM)
    viewModelOf(::TrainVM)
    viewModelOf(::LocomotivesVM)
    viewModelOf(::LocomotiveVM)
    viewModelOf(::WagonsVM)
    viewModelOf(::WagonVM)
    viewModelOf(::StatisticsVM)
    viewModelOf(::TrainTypesVM)
    viewModelOf(::WagonTypeVM)
}