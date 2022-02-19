package pl.mclojek.carpify.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    var newFishPos: LatLng? = null
    val newFishPosObservable: MutableLiveData<LatLng?> = MutableLiveData()

    private val _fishListObservable = MutableLiveData<List<Fish>>()
    val fishListObservable: LiveData<List<Fish>> = _fishListObservable

    private val _fishStatusObservable = MutableLiveData<Resource<String>>()
    val fishStatusObservable: LiveData<Resource<String>> = _fishStatusObservable


    fun load() {
        lake?.let {
            viewModelScope.launch() {
                _fishListObservable.postValue(fishRepository.getFishListForLake(lake!!.id))
                Timber.d("ile tu masz zaciągnięte? ${fishRepository.getFishListForLake(lake!!.id).size}")
                getAllFishFromApi()
            }
        }
    }

    fun getAllFishFromApi() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Timber.d("getujesz te fishe?")
                _fishStatusObservable.postValue(Resource.Loading(""))
                val result = fishRepository.getAllFishFromApi()
                Timber.d("ile masz resultow? ${result.data!!.size}")
                when (result) {
                    is Resource.Success -> {
                        result.data.forEach {
                            Timber.d("Wrzucam do bazy")
                            AppDatabase.getInstance().fishDao().insertFish(it)
                        }
                        _fishStatusObservable.postValue(Resource.Success(""))
                        _fishListObservable.postValue(result.data!!)
                    }
                    is Resource.Error -> {
                        _fishStatusObservable.postValue(
                            Resource.Error(
                                result.message ?: "An error occured"
                            )
                        )
                    }
                    is Resource.Loading -> {
                        _fishStatusObservable.postValue(Resource.Loading())
                    }
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