package pl.mclojek.carpify.domain.repository

import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.model.FishFilter
import pl.mclojek.carpify.domain.model.Lake

interface FishRepository {
    suspend fun getFishListForLake(lakeId: Int): List<Fish>
    suspend fun getFishListForLakeFiltered(lakeId: Int, fishFilter: FishFilter): List<Fish>
    suspend fun getFishListForUser(userId: Int): List<Fish>
    suspend fun getFishListForUserFiltered(userId: Int, fishFilter: FishFilter): List<Fish>
    suspend fun addFish(fish: Fish)
}