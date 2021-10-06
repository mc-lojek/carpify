package pl.mclojek.carpify.domain.repository

import androidx.lifecycle.LiveData
import pl.mclojek.carpify.data.model.Resource
import pl.mclojek.carpify.domain.model.Lake

interface LakeRepository {
    fun getAllLakes(): LiveData<List<Lake>>
    suspend fun getLakesFromApi(): Resource<List<Lake>>
}