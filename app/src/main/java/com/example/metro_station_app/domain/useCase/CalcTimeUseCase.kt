package domain.useCase

import domain.MetroRepository
import domain.model.Station

class CalcTimeUseCase(val repo: MetroRepository) {

    operator fun invoke(count: Int) = count * repo.getTravelTime()
}