package pl.mclojek.carpify.data.repository

import androidx.lifecycle.LiveData
import pl.mclojek.carpify.data.db.AppDatabase
import pl.mclojek.carpify.data.model.Resource
import pl.mclojek.carpify.data.retrofit.RestService
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.domain.repository.LakeRepository
import pl.mclojek.carpify.network.NetworkErrorHandler
import timber.log.Timber

class LakeRepositoryImpl(
    private val restService: RestService
): LakeRepository {
    override fun getAllLakes(): LiveData<List<Lake>> = AppDatabase.getInstance().lakeDao().getAllLakes()

    override suspend fun getLakesFromApi(): Resource<List<Lake>> {
        return try {
            Timber.d("COKOLWIEK??")
            Resource.Success(restService.getAllLakes().map { it.toDomainModel() })
        } catch (t: Throwable) {
            Timber.d("WYJEBALO SIE")
            NetworkErrorHandler.handleException(t)
        }
    }
}