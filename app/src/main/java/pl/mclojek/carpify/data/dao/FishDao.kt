package pl.mclojek.carpify.data.dao

import androidx.room.*
import pl.mclojek.carpify.domain.model.Fish

@Dao
interface FishDao {
    @Query("SELECT * FROM fish")
    suspend fun getAllFish(): List<Fish>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFish(fish: Fish)

    @Delete
    suspend fun delete(fish: Fish)
}