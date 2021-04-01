package pl.mclojek.carpify.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.domain.usecase.GetLakesListUseCase
import pl.mclojek.carpify.network.Repository
import timber.log.Timber

class LakesViewModel(
    private val getLakesListUseCase: GetLakesListUseCase
) : ViewModel() {

    val lakeListObservable: MutableLiveData<List<Lake>> by lazy {
        MutableLiveData<List<Lake>>().also {
            load()
        }
    }

    fun load() {
        viewModelScope.launch() {
            lakeListObservable.value = getLakesListUseCase.getLakesList()
        }
    }

    sealed class LoadLakeListState {
        class LoadLakeListSuccess(val response: ArrayList<Lake>) : LoadLakeListState()
        class LoadLakeListFailure(val errorMessage: String = "") : LoadLakeListState()
        object LoadLakeListFinished : LoadLakeListState()
    }
}