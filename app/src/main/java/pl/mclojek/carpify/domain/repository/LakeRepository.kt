package pl.mclojek.carpify.domain.repository

import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.network.ApiResponse

interface LakeRepository {
    suspend fun getAllLakes(): ApiResponse<List<Lake>>
}