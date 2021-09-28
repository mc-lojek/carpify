package pl.mclojek.carpify.data.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import pl.mclojek.carpify.domain.model.Fish

data class FishDataModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val species: String,
    val weight: Float,
    val size: Int,
    val datetime: Long,
    val bait: String,
    val desc: String,
    val coords: String,
    @SerializedName("lake")
    val lakeId: Int,
    @SerializedName("hunter")
    val hunterId: Int,
    @SerializedName("image")
    val img: String
) {
    fun toDomainModel(): Fish {
        return Fish(
            id=id,
            species=species,
            weight=weight,
            size=size,
            datetime=datetime,
            bait=bait,
            desc=desc,
            coords=coords,
            lakeId=lakeId,
            hunterId=hunterId,
            img=img
        )
    }
}