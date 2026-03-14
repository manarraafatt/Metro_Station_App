package com.example.metro_station_app.data.repo

import com.example.metro_station_app.data.dataSource.MetroDataSource
import data.mapper.MetroMapper
import domain.MetroRepository
import domain.model.Station

class MetroRepositoryImpl(private val dataSource: MetroDataSource) : MetroRepository {
    override fun getStations(): List<Station> {
        return dataSource.loudStation().map { MetroMapper.toDomain(it) }
    }

    override fun getTravelTime(): Int = dataSource.getTravelTime()
    override fun getAllStations(): List<Station> {
        return getStations()
    }
}