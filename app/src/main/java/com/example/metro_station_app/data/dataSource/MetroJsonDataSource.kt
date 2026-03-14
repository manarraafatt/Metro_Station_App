package data.dataSource

import android.content.Context
import com.example.metro_station_app.data.dataSource.MetroDataSource
import com.google.gson.Gson
import data.model.MetroDto
import data.model.StationDto
import domain.model.Station
import java.io.File

class MetroJsonDataSource(private val context: Context): MetroDataSource {

    private val gson = Gson()
    private val fileName = "cairo_metro_structured.json"
    private val dto by lazy {
        val jsonString = context.assets.open(fileName)
            .bufferedReader()
            .use { it.readText() }
        gson.fromJson(jsonString, MetroDto::class.java)
    }

    override fun getTravelTime(): Int = dto.travel_time_between_stations_minutes

    override fun loudStation(): List<StationDto> = dto.stations
}