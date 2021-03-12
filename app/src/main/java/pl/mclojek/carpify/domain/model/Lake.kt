package pl.mclojek.carpify.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import kotlinx.parcelize.Parcelize

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
        return LatLng(bounds.substring(0, 9).toDouble(), bounds.substring(10, 19).toDouble())
    }
}