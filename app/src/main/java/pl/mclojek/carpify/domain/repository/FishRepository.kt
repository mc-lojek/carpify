package pl.mclojek.carpify.domain.repository

import kotlinx.coroutines.flow.Flow
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.util.Resource

interface FishRepository {

    suspend fun getFishForLake(lakeId: String): Flow<Resource<List<Fish>>>

    suspend fun saveFish(fish: Fish): Resource<Fish>

}