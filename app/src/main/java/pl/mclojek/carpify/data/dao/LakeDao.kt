package pl.mclojek.carpify.data.dao

import androidx.room.*
import pl.mclojek.carpify.domain.model.Lake

@Dao
interface LakeDao {
    @Query("SELECT * FROM lake")
    suspend fun getAllLakes(): List<Lake>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLake(lake: Lake)

    @Delete
    suspend fun delete(lake: Lake)
}