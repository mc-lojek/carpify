package pl.mclojek.carpify.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.mclojek.carpify.data.db.AppDatabase
import pl.mclojek.carpify.data.model.LakeDataModel
import pl.mclojek.carpify.data.retrofit.RestService
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.domain.repository.LakeRepository
import pl.mclojek.carpify.network.ApiResponse
import pl.mclojek.carpify.network.NetworkErrorHandler
import retrofit2.HttpException
import timber.log.Timber
import java.lang.Exception

class RemoteLakeRepositoryImpl(
    private val restService: RestService
): LakeRepository {
    override suspend fun getAllLakes(): ApiResponse<List<Lake>> {
        return try {
            Timber.d("COKOLWIEK??")
            ApiResponse.Success(restService.getAllLakes().map { it.toDomainModel() })
        } catch (t: Throwable) {
            Timber.d("WYJEBALO SIE")
            NetworkErrorHandler.handleException(t)
        }
    }
}