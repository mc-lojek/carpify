package pl.mclojek.carpify.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pl.mclojek.carpify.data.Fish

@Dao
interface FishDao {
    @Query("SELECT * FROM fish")
    suspend fun getAllFish(): List<Fish>

    @Insert
    fun insertFish(fish: Fish)

    @Delete
    suspend fun delete(fish: Fish)
}