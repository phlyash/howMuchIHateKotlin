package com.example.myapplication.features.trains.models

class TrainResponse (var count: Int?, var total_pages: Int?, var next: String?,
                     var previous: String?, var size: Int?, var results: List<Train>?) {
}