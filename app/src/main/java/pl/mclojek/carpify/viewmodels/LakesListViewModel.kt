package pl.mclojek.carpify.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.mclojek.carpify.data.Lake
import pl.mclojek.carpify.network.NetworkManager
import pl.mclojek.carpify.network.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class LakesListViewModel : ViewModel() {

    private val lakeListObservable: MutableLiveData<ArrayList<Lake>> by lazy {
        MutableLiveData<ArrayList<Lake>>().also {
            load()
        }
    }

    fun getLakeList(): MutableLiveData<ArrayList<Lake>> {
        return lakeListObservable
    }

    fun load() {
        viewModelScope.launch() {
            lakeListObservable.value = Repository.getAllLakes().domainModel
            Timber.d(lakeListObservable.value?.get(0)?.name)
        }
    }

    sealed class LoadLakeListState {
        class LoadLakeListSuccess(val response: ArrayList<Lake>) : LoadLakeListState()
        class LoadLakeListFailure(val errorMessage: String = "") : LoadLakeListState()
        object LoadLakeListFinished : LoadLakeListState()
    }
}