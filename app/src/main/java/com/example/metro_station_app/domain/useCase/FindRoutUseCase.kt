package domain.useCase

import domain.MetroRepository
import domain.model.RouteResult
import domain.model.Station

class FindRoutUseCase(
    val repo : MetroRepository,
    val calcFareUseCase: CalcFareUseCase,
    val calcTimeUseCase: CalcTimeUseCase,
    val bfsUseCase: BFSUseCase
) {

    operator fun invoke(startName: String ,endName: String): RouteResult {
        val stations = repo.getStations()
        val start = stations.find {
            it.name.equals(
                startName.trim(), true
            ) } ?: return RouteResult.Error("Station not found")
        val end = stations.find {
            it.name.equals(
                endName.trim(), true
            ) } ?: return RouteResult.Error("Station not found")
        val path = bfsUseCase(start, end, stations)
            ?: return RouteResult.Error("path not found")
        val fare = calcFareUseCase(path.size)
        val time = calcTimeUseCase(path.size)
        return RouteResult.Success(
            stations = path,
            fare = fare,
            time = time
        )
    }
}