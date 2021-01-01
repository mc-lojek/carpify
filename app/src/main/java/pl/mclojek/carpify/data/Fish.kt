package pl.mclojek.carpify.data

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fish(
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
) : Parcelable