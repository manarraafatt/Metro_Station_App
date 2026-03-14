package domain.useCase

import domain.MetroRepository
import domain.model.MetroLine

class GetLastStationUseCase(val repo: MetroRepository) {
    operator fun invoke(line: MetroLine): String{
        return repo.getStations()
            .filter { it.line == line }
            .maxBy { it.order }
            .name
    }
}