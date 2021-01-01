package pl.mclojek.carpify.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Lake(
    val id: Int,
    val name: String,
    val voivodeship: String,
    val bounds: String
): Parcelable