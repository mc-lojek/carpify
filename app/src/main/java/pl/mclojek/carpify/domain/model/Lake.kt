package pl.mclojek.carpify.domain.model

import com.google.android.gms.maps.model.LatLngBounds

data class Lake(
    val name: String,
    val description: String,
    val bounds: LatLngBounds,
    val imageUrl: String,
) {
}