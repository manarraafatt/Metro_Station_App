package com.example.metro_station_app.presentation.viewModel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.metro_station_app.domain.useCase.GetAllStationsUseCase
import domain.model.RouteResult
import domain.model.Station
import domain.useCase.FindRoutUseCase

class MetroViewModel(
    private val findRouteUseCase: FindRoutUseCase,
    private val getAllStationsUseCase: GetAllStationsUseCase
) : ViewModel() {

    var isArabic by mutableStateOf(false)
        private set

    var startStation by mutableStateOf("")
    var endStation by mutableStateOf("")

    var result by mutableStateOf<RouteResult?>(null)
        private set

    var stationsList by mutableStateOf<List<Station>>(emptyList())

    init {
        loadStations()
    }
    fun toggleLanguage() {
        isArabic = !isArabic
        startStation = ""
        endStation = ""
        result = null
    }

    fun calculateRoute() {
        if (startStation.isNotBlank() && endStation.isNotBlank()) {
            result = findRouteUseCase(startStation, endStation)
        }
    }
    private fun loadStations() {
        stationsList = getAllStationsUseCase()
    }
}