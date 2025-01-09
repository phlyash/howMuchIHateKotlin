package com.example.myapplication.features.locomotives.models

class LocomotiveResponse(var count: Int?, var total_pages: Int?, var next: String?,
                         var previous: String?, var size: Int?, var results: List<Locomotive>?)
{}