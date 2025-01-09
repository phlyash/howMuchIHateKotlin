package com.example.myapplication.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.myapplication.BASE_URL
import com.example.myapplication.features.locomotives.data.LocomotiveService
import com.example.myapplication.features.statistics.data.StatisticsService
import com.example.myapplication.features.trains.data.TrainService
import com.example.myapplication.features.trains.data.TrainTypeService
import com.example.myapplication.features.wagons.data.WagonService
import com.example.myapplication.features.wagons.data.WagonTypeService

fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

fun provideRetrofit(
    gsonConverterFactory: GsonConverterFactory
): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(gsonConverterFactory)
    .build()

fun provideLocomotiveService(retrofit: Retrofit): LocomotiveService =
    retrofit.create(LocomotiveService::class.java)

fun provideTrainService(retrofit: Retrofit): TrainService =
    retrofit.create(TrainService::class.java)

fun provideWagonService(retrofit: Retrofit): WagonService =
    retrofit.create(WagonService::class.java)

fun provideStatisticsService(retrofit: Retrofit): StatisticsService =
    retrofit.create(StatisticsService::class.java)

fun provideTrainTypeService(retrofit: Retrofit): TrainTypeService =
    retrofit.create(TrainTypeService::class.java)

fun provideWagonTypeService(retrofit: Retrofit): WagonTypeService =
    retrofit.create(WagonTypeService::class.java)

val networkModule = module {
    single { provideConverterFactory() }
    single { provideRetrofit(get()) }
    single { provideLocomotiveService(get()) }
    single { provideTrainService(get()) }
    single { provideWagonService(get()) }
    single { provideStatisticsService(get())}
    single { provideTrainTypeService(get())}
    single { provideWagonTypeService(get())}
}