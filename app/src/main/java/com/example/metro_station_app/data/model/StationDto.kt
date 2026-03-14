package data.model

import com.google.gson.annotations.SerializedName
import domain.model.MetroLine

data class StationDto (
    val id : Int,
    val name : String,
    val line: String,
    val order : Int,
    @SerializedName("is_transfer") val isTransfer : Boolean,
    @SerializedName("transfer_lines") val transferLines : List<String>
)