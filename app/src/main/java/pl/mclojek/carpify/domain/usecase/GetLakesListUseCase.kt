package pl.mclojek.carpify.domain.usecase

import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.domain.repository.FishRepository
import pl.mclojek.carpify.domain.repository.LakeRepository
import pl.mclojek.carpify.network.ApiResponse

class GetLakesListUseCase(private val repository: LakeRepository) {
    suspend fun getLakesList(): ApiResponse<List<Lake>> {
        return repository.getAllLakes()
    }
}