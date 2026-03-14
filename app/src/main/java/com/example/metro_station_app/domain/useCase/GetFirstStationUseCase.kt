package domain.useCase

import domain.MetroRepository
import domain.model.MetroLine

class GetFirstStationUseCase(val repo: MetroRepository) {
    fun execute(line: MetroLine):String{
        return repo.getStations()
            .filter { it.line == line }
            .minBy { it.order }
            .name
    }
}