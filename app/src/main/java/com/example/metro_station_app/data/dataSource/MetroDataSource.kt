package com.example.metro_station_app.data.dataSource

import data.model.StationDto

interface MetroDataSource {
    fun getTravelTime(): Int
    fun loudStation(): List<StationDto>
}