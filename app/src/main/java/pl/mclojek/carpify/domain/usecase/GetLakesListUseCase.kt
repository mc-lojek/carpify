package pl.mclojek.carpify.domain.usecase

import pl.mclojek.carpify.data.model.Resource
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.domain.repository.FishRepository
import pl.mclojek.carpify.domain.repository.LakeRepository

class GetLakesListUseCase(private val repository: LakeRepository) {
    suspend fun getLakesList(): Resource<List<Lake>> {
        return repository.getAllLakes()
    }
}