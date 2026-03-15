package data.mapper

import data.model.StationDto
import domain.model.MetroLine
import domain.model.Station

object MetroMapper {

    fun toDomain(dto : StationDto): Station {
        return Station(
            id = dto.id,
            name = dto.name,
            line = dto.line.toMetroLine(),
            order = dto.order,
            isTransfer = dto.isTransfer,
            transferLines = dto.transferLines.map { it.toMetroLine() },
        )
    }

    private fun String.toMetroLine() : MetroLine =
        when (this.trim().uppercase()) {
            "LINE_1", "FIRST LINE", "1" -> MetroLine.LINE_1
            "LINE_2", "SECOND LINE", "2" -> MetroLine.LINE_2
            "LINE_3", "THIRD LINE", "3" -> MetroLine.LINE_3
            "الخط الأول" -> MetroLine.LINE_1
            "الخط الثاني" -> MetroLine.LINE_2
            "الخط الثالث" -> MetroLine.LINE_3
            else -> throw IllegalArgumentException("Unknown line: $this")
        }
}