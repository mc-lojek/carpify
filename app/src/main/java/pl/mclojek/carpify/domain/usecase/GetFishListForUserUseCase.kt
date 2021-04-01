package pl.mclojek.carpify.domain.usecase

import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.model.FishFilter
import pl.mclojek.carpify.domain.repository.FishRepository

class GetFishListForUserUseCase(private val repository: FishRepository) {
    suspend fun getFishListForUser(userId: Int): List<Fish> {
        return repository.getFishListForUser(userId)
    }

    suspend fun getFishListForUserFiltered(userId: Int, fishFilter: FishFilter): List<Fish> {
        return repository.getFishListForUserFiltered(userId, fishFilter)
    }
}