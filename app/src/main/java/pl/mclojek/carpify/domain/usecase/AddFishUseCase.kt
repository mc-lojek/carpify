package pl.mclojek.carpify.domain.usecase

import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.model.FishFilter
import pl.mclojek.carpify.domain.repository.FishRepository

class AddFishUseCase(private val repository: FishRepository) {
    suspend fun addFish(fish: Fish) {
        repository.addFish(fish)
    }

    suspend fun getFishListForLakeFiltered(lakeId: Int, fishFilter: FishFilter): List<Fish> {
        return repository.getFishListForLakeFiltered(lakeId, fishFilter)
    }
}