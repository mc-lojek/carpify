package pl.mclojek.carpify.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.mclojek.carpify.data.db.AppDatabase
import pl.mclojek.carpify.data.model.Resource
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.domain.repository.LakeRepository

class LakeRepositoryImpl: LakeRepository {
    override suspend fun getAllLakes(): Resource<List<Lake>> {
        return withContext(Dispatchers.IO) {
            return@withContext Resource.Success(AppDatabase.getInstance().lakeDao().getAllLakes())
        }
    }
}