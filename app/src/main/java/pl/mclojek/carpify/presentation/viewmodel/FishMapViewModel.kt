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
import pl.mclojek.carpify.domain.model.fakeFishList
import pl.mclojek.carpify.presentation.state.FishListState
import javax.inject.Inject

@HiltViewModel
class FishMapViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(FishListState())

    fun load(lakeId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(isLoading = true)
            delay(3000)
            state = FishListState(
                fishList = fakeFishList.filter { it.lakeId == lakeId },
                isLoading = false,
                error = null
            )
        }
    }
}