package pl.mclojek.carpify.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.model.FishFilter
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.domain.usecase.GetFishListForLakeUseCase
import timber.log.Timber

class FishMapViewModel(
        private val getFishListForLakeUseCase: GetFishListForLakeUseCase
) : ViewModel() {

    var lake: Lake? = null
    var fishFilter: FishFilter = FishFilter()

    val fishListObservable: MutableLiveData<List<Fish>> by lazy {
        MutableLiveData<List<Fish>>().also {
            load()
        }
    }

    fun load() {
        viewModelScope.launch() {
            fishListObservable.value = getFishListForLakeUseCase.getFishListForLakeFiltered(lake?.id!!, fishFilter)
        }
    }

}