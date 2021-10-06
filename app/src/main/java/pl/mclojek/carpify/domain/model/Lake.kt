package pl.mclojek.carpify.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import kotlinx.parcelize.Parcelize
import pl.mclojek.carpify.data.model.LakeDataModel

@Entity
@Parcelize
data class Lake(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val voivodeship: String,
    val bounds: String
): Parcelable {

    fun getCenterLatLng(): LatLng {
        return getLatLngBounds().center
    }

    fun getLatLngBounds(): LatLngBounds {
        val splitted = bounds.split(",").map { it.toDouble() }
        return LatLngBounds(LatLng(splitted[0], splitted[1]), LatLng(splitted[2], splitted[3]))
    }

    fun toDataModel(): LakeDataModel {
        return LakeDataModel(id, name, bounds, voivodeship)
    }
}