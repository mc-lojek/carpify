package pl.mclojek.carpify.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pl.mclojek.carpify.domain.repository.FishRepository
import pl.mclojek.carpify.domain.util.Resource
import pl.mclojek.carpify.presentation.state.FishListState
import javax.inject.Inject

@HiltViewModel
class FishMapViewModel @Inject constructor(
    private val fishRepository: FishRepository
) : ViewModel() {

    var state by mutableStateOf(FishListState())

    fun load(lakeId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(isLoading = true)
            delay(300)
            fishRepository.getFishForLake(lakeId).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { fishList ->
                            state = FishListState(fishList = fishList)
                        }
                    }
                    is Resource.Error -> {
                        state = FishListState(error = "")
                    }
                    is Resource.Loading -> {

                    }
                }
            }
        }
    }
}