package pl.mclojek.carpify.presentation.viewmodel

import androidx.lifecycle.*
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.mclojek.carpify.data.db.AppDatabase
import pl.mclojek.carpify.data.model.Resource
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.model.FishFilter
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.domain.repository.FishRepository
import timber.log.Timber

class FishMapViewModel(
    private val fishRepository: FishRepository,
) : ViewModel() {

    var lake: Lake? = null
    var fishFilter: FishFilter = FishFilter()
    val newFishPosObservable: MutableLiveData<LatLng?> = MutableLiveData()

    private val fishListMerger =  MediatorLiveData<List<Fish>>()
    val fishListObservable = fishListMerger as LiveData<List<Fish>>
    private lateinit var currentFishListObservable: LiveData<List<Fish>>

    fun load() {
        lake?.let {
            viewModelScope.launch() {
                currentFishListObservable = fishRepository.getFishListForLake(lake!!.id)
                fishListMerger.addSource(currentFishListObservable) {
                    fishListMerger.value = it
                }
            }
        }
    }

    fun loadFiltered() {
        lake?.let {
            viewModelScope.launch() {
                fishListMerger.removeSource(currentFishListObservable)
                currentFishListObservable = fishRepository.getFishListForLakeFiltered(lake!!.id, fishFilter)
                fishListMerger.addSource(currentFishListObservable) {
                    fishListMerger.value = it
                }
            }
        }
    }

    fun addFish(fish: Fish) {
        viewModelScope.launch {
            fishRepository.addFish(fish)
        }
    }
}