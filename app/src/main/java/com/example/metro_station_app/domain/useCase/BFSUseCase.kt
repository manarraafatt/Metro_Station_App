package domain.useCase

import domain.model.MetroLine
import domain.model.Station
import java.util.LinkedList
import java.util.Queue

class BFSUseCase {
    operator fun invoke(
        startStation: Station,
        endStation: Station,
        stations: List<Station>
    ): List<Station>? {

        if (startStation == endStation) return listOf(startStation)

        val queue : Queue<Station> = LinkedList()
        val visited = mutableSetOf<Station>()
        val parent = mutableMapOf<Station, Station?>()

        queue.add(startStation)
        visited.add(startStation)
        parent[startStation] = null

        val stationByLine = stations.groupBy { it.line }
        val stationsByName = stations.groupBy { it.name }

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            if (current.name.equals(endStation.name, ignoreCase = true)) {
                return buldPath(current, parent)
            }

            val nieghbors = getNieghbors(current , stationByLine , stationsByName)
            for (nieghbor in nieghbors) {
                if (nieghbor !in visited) {
                    visited.add(nieghbor)
                    queue.add(nieghbor)
                    parent[nieghbor] = current
                }
            }
        }

        return null
    }

    private fun getNieghbors(
        station: Station,
        stationByLine: Map<MetroLine , List<Station>>,
        stationByName : Map<String, List<Station>>
    ): List<Station> {
        val sameLine = stationByLine[station.line]
            ?.filter { it.order == station.order + 1 || it.order == station.order - 1 }
            ?: emptyList()
        val transfers = if (station.isTransfer){
            stationByName[station.name]
                ?.filter { it.line != station.line } ?: emptyList()
        }else{
            emptyList<Station>()
        }

        return sameLine + transfers
    }
}

private fun buldPath(
    endStation: Station,
    parent : Map<Station, Station?>): List<Station> {
    val path = mutableListOf<Station>()
    var current: Station? = endStation

    while (current != null) {
        path.add(current)
        current = parent[current]
    }
    return path.reversed()
}