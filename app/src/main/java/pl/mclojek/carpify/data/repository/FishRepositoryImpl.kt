package pl.mclojek.carpify.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.model.fakeFishList
import pl.mclojek.carpify.domain.repository.FishRepository
import pl.mclojek.carpify.domain.util.Resource

class FishRepositoryImpl : FishRepository {

    override suspend fun getFishForLake(lakeId: String): Flow<Resource<List<Fish>>> {
        return flow {
            emit(Resource.Success(fakeFishList.filter { it.lakeId == lakeId }))
        }
    }

    override suspend fun saveFish(fish: Fish): Resource<Fish> {
        fakeFishList.add(fish)
        return Resource.Success(fish)
    }
}