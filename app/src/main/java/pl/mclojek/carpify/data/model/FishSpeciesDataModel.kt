package pl.mclojek.carpify.data.model

import com.google.gson.annotations.SerializedName

data class FishSpeciesDataModel(
    val id: Long,
    val name: String,
    @SerializedName("name_verbose")
    val nameVerbose: String
)

data class FishSpeciesWrapper(
    val species: List<FishSpeciesDataModel>
)