package pl.mclojek.carpify.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.mclojek.carpify.data.db.AppDatabase
import pl.mclojek.carpify.data.model.Resource
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.domain.repository.LakeRepository

class LakesViewModel(
    private val lakeRepository: LakeRepository
) : ViewModel() {

    val lakesListObservable: LiveData<List<Lake>> = lakeRepository.getAllLakes()
        .also { loadLakesFromApi() }

    private val _lakesStatusObservable = MutableLiveData<Resource<String>>()
    val lakesStatusObservable: LiveData<Resource<String>> = _lakesStatusObservable

    private fun loadLakesFromApi() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _lakesStatusObservable.postValue(Resource.Loading(""))
                val result = lakeRepository.getLakesFromApi()
                when (result) {
                    is Resource.Success -> {
                        result.data!!.forEach {
                            AppDatabase.getInstance().lakeDao().insertLake(it)
                        }
                        _lakesStatusObservable.postValue(Resource.Success(""))
                    }
                    is Resource.Error -> {
                        _lakesStatusObservable.postValue(
                            Resource.Error(
                                result.message ?: "An error occured"
                            )
                        )
                    }
                    is Resource.Loading -> {
                        _lakesStatusObservable.postValue(Resource.Loading())
                    }
                }
            }

        }
    }
}