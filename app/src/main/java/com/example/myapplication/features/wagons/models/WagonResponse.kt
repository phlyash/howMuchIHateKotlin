package com.example.myapplication.features.wagons.models

class WagonResponse(var count: Int?, var total_pages: Int?, var next: String?,
                    var previous: String?, var size: Int?, var results: List<Wagon>?) {
}