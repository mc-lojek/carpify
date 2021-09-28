package pl.mclojek.carpify.presentation.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import pl.mclojek.carpify.data.model.Resource
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.domain.usecase.GetLakesListUseCase

class LakesViewModel(
    private val getLakesListUseCase: GetLakesListUseCase
) : ViewModel() {

    val lakesListObservable: LiveData<Resource<List<Lake>>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        emit(getLakesListUseCase.getLakesList())
    }
}