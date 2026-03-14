package com.example.metro_station_app.domain.useCase

import domain.MetroRepository
import domain.model.Station

class GetAllStationsUseCase(private val repo: MetroRepository) {
    operator fun invoke(): List<Station> = repo.getStations()
}