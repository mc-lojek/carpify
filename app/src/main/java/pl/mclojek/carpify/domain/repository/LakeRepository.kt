package pl.mclojek.carpify.domain.repository

import pl.mclojek.carpify.domain.model.Lake

interface LakeRepository {
    suspend fun getAllLakes(): List<Lake>
}