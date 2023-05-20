package pl.mclojek.carpify.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.mclojek.carpify.domain.model.AllSpecies
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.repository.FishRepository
import pl.mclojek.carpify.presentation.state.FishListState
import java.time.ZonedDateTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddFishViewModel @Inject constructor(
    private val fishRepository: FishRepository
) : ViewModel() {

    fun addFish(
        lakeId: String,
        weight: Float
    ) {
        viewModelScope.launch {
            fishRepository.saveFish(
                Fish(
                    id = UUID.randomUUID().toString(),
                    species = AllSpecies.random(),
                    weight = weight,
                    length = 123,
                    catchDatetime = ZonedDateTime.now(),
                    catchPosition = LatLng(54.0, 21.0),
                    description = "",
                    photoUrl = "https://gardnertackle.co.uk/wp-content/uploads/2015/12/chubby-chops.jpg",
                    lakeId = lakeId
                )
            )
        }
    }
}