package pl.mclojek.carpify.presentation.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.domain.usecase.GetLakesListUseCase
import pl.mclojek.carpify.network.ApiResponse
import pl.mclojek.carpify.network.Repository
import timber.log.Timber
import java.lang.Error

class LakesViewModel(
    private val getLakesListUseCase: GetLakesListUseCase
) : ViewModel() {

    val lakesListObservable: LiveData<ApiResponse<List<Lake>>> = liveData(Dispatchers.IO) {
        emit(ApiResponse.Loading())
        emit(getLakesListUseCase.getLakesList())
    }
}