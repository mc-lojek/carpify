package pl.mclojek.carpify.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Lake(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val voivodeship: String,
    val bounds: String
): Parcelable