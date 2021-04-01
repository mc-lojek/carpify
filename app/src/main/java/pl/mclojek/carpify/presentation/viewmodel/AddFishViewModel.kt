package pl.mclojek.carpify.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.usecase.AddFishUseCase

class AddFishViewModel(
        private val addFishUseCase: AddFishUseCase
) : ViewModel() {

    var fish: Fish? = null
    var position: LatLng? = null
}