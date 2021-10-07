package pl.mclojek.carpify.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.model.FishFilter
import pl.mclojek.carpify.domain.repository.FishRepository

class MyFishViewModel(
    private val fishRepository: FishRepository
) : ViewModel() {

    //TODO: temporary hardcoded to 1
    var userId: Int = 1
    var fishFilter: FishFilter = FishFilter()

    //TODO

    //not working

    val fishListObservable: MutableLiveData<List<Fish>> by lazy {
        MutableLiveData<List<Fish>>().also {
            load()
        }
    }

    fun load() {
        viewModelScope.launch() {
            fishListObservable.value = fishRepository.getFishListForUserFiltered(userId, fishFilter)
        }
    }
}