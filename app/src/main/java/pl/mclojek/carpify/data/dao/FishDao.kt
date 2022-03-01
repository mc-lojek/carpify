package pl.mclojek.carpify.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import pl.mclojek.carpify.domain.model.Fish

@Dao
interface FishDao {
    @Query("SELECT * FROM fish")
    fun getAllFish(): LiveData<List<Fish>>

    @Query("SELECT * FROM fish WHERE lakeId = :lakeId ")
    fun getFishForLake(lakeId: Int): LiveData<List<Fish>>

    @Query("SELECT * FROM fish WHERE lakeId = :lakeId AND species IN (:speciesList) AND datetime > :timeFrom AND datetime < :timeTo AND weight > :weightFrom AND weight < :weightTo AND size > :lengthFrom AND size < :lengthTo")
    fun getFishForLakeFiltered(
        lakeId: Int,
        speciesList: List<String>,
        timeFrom: Long,
        timeTo: Long,
        weightFrom: Float,
        weightTo: Float,
        lengthFrom: Int,
        lengthTo: Int
    ): LiveData<List<Fish>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFish(fish: Fish)

    @Delete
    suspend fun delete(fish: Fish)
}