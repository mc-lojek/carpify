package pl.mclojek.carpify.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

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
) : Parcelable