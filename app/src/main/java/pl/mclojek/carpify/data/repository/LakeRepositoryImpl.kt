package pl.mclojek.carpify.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.mclojek.carpify.data.db.AppDatabase
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.domain.repository.LakeRepository
import pl.mclojek.carpify.network.ApiResponse

class LakeRepositoryImpl: LakeRepository {
    override suspend fun getAllLakes(): ApiResponse<List<Lake>> {
        return withContext(Dispatchers.IO) {
            return@withContext ApiResponse.Success(AppDatabase.getInstance().lakeDao().getAllLakes())
        }
    }
}