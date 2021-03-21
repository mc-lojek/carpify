package pl.mclojek.carpify.domain.usecase

import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.model.FishFilter
import pl.mclojek.carpify.domain.repository.FishRepository
import timber.log.Timber

class GetFishListForLakeUseCase(private val repository: FishRepository) {
    suspend fun getFishListForLake(lakeId: Int): List<Fish> {
        return repository.getFishListForLake(lakeId)
    }

    suspend fun getFishListForLakeFiltered(lakeId: Int, fishFilter: FishFilter): List<Fish> {
        return repository.getFishListForLakeFiltered(lakeId, fishFilter)
    }
}