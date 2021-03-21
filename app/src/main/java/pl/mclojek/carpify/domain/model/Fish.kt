package pl.mclojek.carpify.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

@Entity
@Parcelize
data class Fish(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val species: String,
    val weight: Float,
    val size: Int,
    val datetime: Long,
    val bait: String,
    val desc: String,
    val coords: String,
    val lakeId: Int,
    val hunterId: Int,
    val img: String
) : Parcelable {

    fun getLatLng(): LatLng {
        val splitted = coords.split(",").map { it.toDouble() }
        return LatLng(splitted[0], splitted[1])
    }

    fun getDateString(): String {
        val sdf = SimpleDateFormat("dd.MM.yyyy hh:mm")
        val date = Date(datetime)
        return sdf.format(date)
    }

}