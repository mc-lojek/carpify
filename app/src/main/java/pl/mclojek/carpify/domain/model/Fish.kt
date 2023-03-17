package pl.mclojek.carpify.domain.model

import com.google.android.gms.maps.model.LatLng
import java.time.ZonedDateTime

data class Fish(
    val id: String,
    val species: String,
    val weight: Float,
    val length: Int,
    val catchDatetime: ZonedDateTime,
    val catchPosition: LatLng,
    val description: String,
    val photoUrl: String,
    val lakeId: String,
)