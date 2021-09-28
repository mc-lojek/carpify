package pl.mclojek.carpify.domain.repository

import pl.mclojek.carpify.data.model.Resource
import pl.mclojek.carpify.domain.model.Lake

interface LakeRepository {
    suspend fun getAllLakes(): Resource<List<Lake>>
}