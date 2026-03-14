package domain

import domain.model.Station

interface MetroRepository {

    fun getStations() : List<Station>

    fun getTravelTime() : Int

    fun getAllStations(): List<Station>
}