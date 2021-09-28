package pl.mclojek.carpify.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import pl.mclojek.carpify.data.model.Resource
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.repository.FishRepository
import pl.mclojek.carpify.domain.usecase.AddFishUseCase