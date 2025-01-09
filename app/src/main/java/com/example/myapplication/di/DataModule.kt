package com.example.myapplication.di

import com.example.myapplication.features.locomotives.data.LocomotivePagingSource
import com.example.myapplication.features.trains.data.TrainPagingSource
import com.example.myapplication.features.locomotives.data.LocomotiveRepository
import com.example.myapplication.features.locomotives.data.LocomotiveRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.myapplication.features.trains.data.TrainRepository
import com.example.myapplication.features.trains.data.TrainRepositoryImpl
import com.example.myapplication.features.wagons.data.WagonRepository
import com.example.myapplication.features.wagons.data.WagonRepositoryImpl
import com.example.myapplication.features.trains.data.TrainTypeRepository
import com.example.myapplication.features.trains.data.TrainTypeRepositoryImpl
import com.example.myapplication.features.wagons.data.WagonPagingSource
import com.example.myapplication.features.wagons.data.WagonTypeRepository
import com.example.myapplication.features.wagons.data.WagonTypeRepositoryImpl


val dataModule = module {
    singleOf(::TrainRepositoryImpl) {
        bind<TrainRepository>()
    }
    singleOf(::LocomotiveRepositoryImpl) {
        bind<LocomotiveRepository>()
    }
    singleOf(::WagonRepositoryImpl) {
        bind<WagonRepository>()
    }
    singleOf(::WagonTypeRepositoryImpl) {
        bind<WagonTypeRepository>()
    }
    singleOf(::TrainTypeRepositoryImpl) {
        bind<TrainTypeRepository>()
    }
    singleOf(::TrainPagingSource) {
        bind<TrainPagingSource>()
    }
    singleOf(::LocomotivePagingSource) {
        bind<LocomotivePagingSource>()
    }
    singleOf(::WagonPagingSource) {
        bind<WagonPagingSource>()
    }
}
