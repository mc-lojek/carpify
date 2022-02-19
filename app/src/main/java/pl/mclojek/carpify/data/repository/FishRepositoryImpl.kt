package pl.mclojek.carpify.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.mclojek.carpify.data.db.AppDatabase
import pl.mclojek.carpify.data.model.FishDataModel
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

    override suspend fun getFishListForUserFiltered(
        userId: Int,
        fishFilter: FishFilter
    ): List<Fish> =
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
            //Resource.Success(restService.getAllFish().map { it.toDomainModel() })
            Resource.Success(getMockFishList().map { it.toDomainModel() })
        } catch (t: Throwable) {
            NetworkErrorHandler.handleException(t)
        }
    }

    private suspend fun getMockFishList(): List<FishDataModel> {
        return listOf(
            FishDataModel(
                1,
                "Carp",
                13.3f,
                123,
                1644118467,
                "",
                "",
                "54.351885, 18.118626",
                1,
                1,
                "https://images.unsplash.com/photo-1580757468214-c73f7062a5cb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1000&q=80"
            ),
            FishDataModel(
                2,
                "Grass carp",
                6.1f,
                65,
                1644112267,
                "",
                "",
                "54.355635, 18.123547",
                1,
                1,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5CJYIL7aNy1ZbriKfmDhewDTMDAKqVsdVQA&usqp=CAU"
            ),
            FishDataModel(
                3,
                "Carp",
                13.3f,
                123,
                1644118467,
                "",
                "",
                "54.349017, 18.108027",
                1,
                1,
                "https://www.karpmax.pl/media_pliki/news_foto/6424_prowadzenie.png"
            ),
        )
    }
}