package pl.mclojek.carpify.data.repository

import pl.mclojek.carpify.data.retrofit.RestService
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.model.FishFilter
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.domain.repository.FishRepository

class RemoteFishRepositoryImpl(
    private val restService: RestService
): FishRepository {
    override suspend fun getFishListForLake(lakeId: Int): List<Fish> {
        return restService.getAllFish().map { it.toDomainModel() }
    }

    override suspend fun getFishListForLakeFiltered(
        lakeId: Int,
        fishFilter: FishFilter
    ): List<Fish> {
        return restService.getAllFish().map { it.toDomainModel() }
    }

    override suspend fun getFishListForUser(userId: Int): List<Fish> {
        TODO("Not yet implemented")
    }

    override suspend fun getFishListForUserFiltered(
        userId: Int,
        fishFilter: FishFilter
    ): List<Fish> {
        TODO("Not yet implemented")
    }

    override suspend fun addFish(fish: Fish) {
        TODO("Not yet implemented")
    }
}