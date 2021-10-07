package pl.mclojek.carpify.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.mclojek.carpify.data.db.AppDatabase
import pl.mclojek.carpify.data.model.Resource
import pl.mclojek.carpify.data.retrofit.RestService
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.model.FishFilter
import pl.mclojek.carpify.domain.repository.FishRepository
import pl.mclojek.carpify.network.NetworkErrorHandler
import timber.log.Timber

class FishRepositoryImpl(
    private val restService: RestService
) : FishRepository {
    override suspend fun getFishListForLake(lakeId: Int): List<Fish> {
        val res = AppDatabase.getInstance().fishDao().getFishForLake(lakeId)
        Timber.d("to sie wywoluje lul ${res.size}")
        return res
    }


    override suspend fun getFishListForLakeFiltered(lakeId: Int, fishFilter: FishFilter): List<Fish> =
        withContext(Dispatchers.IO) {
            AppDatabase.getInstance().fishDao().getFishForLakeFiltered(
                lakeId,
                fishFilter.speciesList,
                fishFilter.timeFrom,
                fishFilter.timeTo,
                fishFilter.weightFrom,
                fishFilter.weightTo,
                fishFilter.lengthFrom,
                fishFilter.lengthTo
            )
        }


    override suspend fun getFishListForUser(userId: Int): List<Fish> =
        AppDatabase.getInstance().fishDao().getFishForUser(userId)

    override suspend fun getFishListForUserFiltered(userId: Int, fishFilter: FishFilter): List<Fish> =
        AppDatabase.getInstance().fishDao().getFishForUserFiltered(
            userId,
            fishFilter.speciesList,
            fishFilter.timeFrom,
            fishFilter.timeTo,
            fishFilter.weightFrom,
            fishFilter.weightTo,
            fishFilter.lengthFrom,
            fishFilter.lengthTo
        )

    override suspend fun addFish(fish: Fish): Resource<String> {
        withContext(Dispatchers.IO) {
            AppDatabase.getInstance().fishDao().insertFish(fish)
        }
        return Resource.Success("")
    }

    override suspend fun getAllFishFromApi(): Resource<List<Fish>> {
        return try {
            Timber.d("COKOLWIEK esfdsfsdfs??")
            Resource.Success(restService.getAllFish().map { it.toDomainModel() })
        } catch (t: Throwable) {
            Timber.d("WYJEBALO SIEdfsfdsdsfdsfds")
            NetworkErrorHandler.handleException(t)
        }
    }
}