package pl.mclojek.carpify.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import pl.mclojek.carpify.domain.model.Lake

@Dao
interface LakeDao {
    @Query("SELECT * FROM lake")
    fun getAllLakes(): LiveData<List<Lake>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLake(lake: Lake)

    @Delete
    suspend fun delete(lake: Lake)
}