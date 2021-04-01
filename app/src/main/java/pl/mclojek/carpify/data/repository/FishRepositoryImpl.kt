package pl.mclojek.carpify.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.mclojek.carpify.data.db.AppDatabase
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.model.FishFilter
import pl.mclojek.carpify.domain.repository.FishRepository

class FishRepositoryImpl: FishRepository {
    override suspend fun  getFishListForLake(lakeId: Int): List<Fish> {
        return withContext(Dispatchers.IO) {
            return@withContext AppDatabase.getInstance().fishDao().getFishForLake(lakeId)
        }
    }
    override suspend fun  getFishListForLakeFiltered(lakeId: Int, fishFilter: FishFilter): List<Fish> {
        return withContext(Dispatchers.IO) {
            return@withContext AppDatabase.getInstance().fishDao().getFishForLakeFiltered(lakeId, fishFilter.speciesList, fishFilter.timeFrom, fishFilter.timeTo, fishFilter.weightFrom, fishFilter.weightTo, fishFilter.lengthFrom, fishFilter.lengthTo)
        }
    }

    override suspend fun  getFishListForUser(userId: Int): List<Fish> {
        return withContext(Dispatchers.IO) {
            return@withContext AppDatabase.getInstance().fishDao().getFishForUser(userId)
        }
    }
    override suspend fun  getFishListForUserFiltered(userId: Int, fishFilter: FishFilter): List<Fish> {
        return withContext(Dispatchers.IO) {
            return@withContext AppDatabase.getInstance().fishDao().getFishForUserFiltered(userId, fishFilter.speciesList, fishFilter.timeFrom, fishFilter.timeTo, fishFilter.weightFrom, fishFilter.weightTo, fishFilter.lengthFrom, fishFilter.lengthTo)
        }
    }

    override suspend fun addFish(fish: Fish) {
        return withContext(Dispatchers.IO) {
            return@withContext AppDatabase.getInstance().fishDao().insertFish(fish)
        }
    }
}