package pl.mclojek.carpify.data.repository

import pl.mclojek.carpify.data.model.Resource
import pl.mclojek.carpify.data.retrofit.RestService
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.model.FishFilter
import pl.mclojek.carpify.domain.repository.FishRepository
import pl.mclojek.carpify.network.NetworkErrorHandler
import timber.log.Timber

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

    override suspend fun addFish(fish: Fish): Resource<String> {
        return try {
            Timber.d("COKOLWIEK??")
            Resource.Success(restService.sendFish(fish.toDataModel()))
        } catch (t: Throwable) {
            Timber.d("WYJEBALO SIE")
            NetworkErrorHandler.handleException(t)
        }
    }
}