package com.example.myapplication.features.trains.models

class Train(var id: Int, var name: String, var locomotive: Int, var route: Int,
            var train_type: Int) {
    lateinit var train_type_name: String
    lateinit var locomotive_name: String
}