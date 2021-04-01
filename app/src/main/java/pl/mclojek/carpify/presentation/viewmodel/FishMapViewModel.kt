package pl.mclojek.carpify.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.model.FishFilter
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.domain.usecase.AddFishUseCase
import pl.mclojek.carpify.domain.usecase.GetFishListForLakeUseCase
import timber.log.Timber

class FishMapViewModel(
        private val getFishListForLakeUseCase: GetFishListForLakeUseCase,
        private val addFishUseCase: AddFishUseCase
) : ViewModel() {

    var lake: Lake? = null
    var fishFilter: FishFilter = FishFilter()
    var newFishPos: LatLng? = null
    val newFishPosObservable: MutableLiveData<LatLng?> = MutableLiveData()

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

    fun addFish(fish: Fish) {
        viewModelScope.launch {
            addFishUseCase.addFish(fish)
        }
    }

}