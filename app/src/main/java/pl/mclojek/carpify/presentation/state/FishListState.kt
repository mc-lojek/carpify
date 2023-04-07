package pl.mclojek.carpify.presentation.state

import pl.mclojek.carpify.domain.model.Fish

data class FishListState(
    val fishList: List<Fish> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)