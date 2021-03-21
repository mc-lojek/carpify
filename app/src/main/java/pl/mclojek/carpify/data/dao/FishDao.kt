package pl.mclojek.carpify.data.dao

import androidx.room.*
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.model.FishFilter

@Dao
interface FishDao {
    @Query("SELECT * FROM fish")
    suspend fun getAllFish(): List<Fish>

    @Query("SELECT * FROM fish WHERE lakeId = :lakeId ")
    suspend fun getFishForLake(lakeId: Int): List<Fish>

    @Query("SELECT * FROM fish WHERE lakeId = :lakeId AND species IN (:speciesList) AND datetime > :timeFrom AND datetime < :timeTo AND weight > :weightFrom AND weight < :weightTo AND size > :lengthFrom AND size < :lengthTo")
    suspend fun getFishForLakeFiltered(lakeId: Int,
                                       speciesList: List<String>,
                                       timeFrom: Long,
                                       timeTo: Long,
                                       weightFrom: Float,
                                       weightTo: Float,
                                       lengthFrom: Int,
                                       lengthTo: Int): List<Fish>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFish(fish: Fish)

    @Delete
    suspend fun delete(fish: Fish)
}