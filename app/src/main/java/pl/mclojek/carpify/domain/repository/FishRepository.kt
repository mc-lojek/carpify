package pl.mclojek.carpify.domain.repository

import androidx.lifecycle.LiveData
import pl.mclojek.carpify.data.model.Resource
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.model.FishFilter
import pl.mclojek.carpify.domain.model.Lake

interface FishRepository {
    fun getFishListForLake(lakeId: Int): LiveData<List<Fish>>
    fun getFishListForLakeFiltered(lakeId: Int, fishFilter: FishFilter): LiveData<List<Fish>>
    suspend fun addFish(fish: Fish): Resource<String>
}