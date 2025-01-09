package com.example.myapplication.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.myapplication.BASE_URL
import com.example.myapplication.features.locomotives.data.LocomotiveService
import com.example.myapplication.features.trains.data.TrainService
import com.example.myapplication.features.wagons.data.WagonService
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

val networkModule = module {
    single { provideConverterFactory() }
    single { provideRetrofit(get()) }
    single { provideLocomotiveService(get()) }
    single { provideTrainService(get()) }
    single { provideWagonService(get()) }
}