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
import pl.mclojek.carpify.domain.usecase.GetFishListForUserUseCase

class MyFishViewModel(
    private val getFishListForUserUseCase: GetFishListForUserUseCase
) : ViewModel() {


    //TODO: temporary hardcoded to 1
    var userId: Int = 1
    var fishFilter: FishFilter = FishFilter()

    val fishListObservable: MutableLiveData<List<Fish>> by lazy {
        MutableLiveData<List<Fish>>().also {
            load()
        }
    }

    fun load() {
        viewModelScope.launch() {
            fishListObservable.value = getFishListForUserUseCase.getFishListForUserFiltered(userId, fishFilter)
        }
    }
}