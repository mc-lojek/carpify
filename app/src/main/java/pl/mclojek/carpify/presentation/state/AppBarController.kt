package pl.mclojek.carpify.presentation.state

import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.vector.ImageVector


object AppBarController {
    fun updateTitle(appBarState: MutableState<AppBarState>, title: String) {
        appBarState.value = appBarState.value.copy(title = title)
    }

    fun updateSearchText(appBarState: MutableState<AppBarState>, newText: String) {
        appBarState.value = appBarState.value.copy(searchText = newText)
    }

    fun changeToDefaultMode(appBarState: MutableState<AppBarState>) {
        appBarState.value = appBarState.value.copy(searchingMode = false)
    }

    fun changeToSearchMode(appBarState: MutableState<AppBarState>) {
        appBarState.value = appBarState.value.copy(searchingMode = true)
    }

    fun onBackPressed(appBarState: MutableState<AppBarState>, callback: () -> Unit) {
        appBarState.value = appBarState.value.copy(onBackPressed = callback)
    }

    fun setOnIconClick(
        appBarState: MutableState<AppBarState>,
        iconIndex: Int,
        callback: () -> Unit
    ) {
        when (iconIndex) {
            1 -> appBarState.value = appBarState.value.copy(onIcon1Click = callback)
            2 -> appBarState.value = appBarState.value.copy(onIcon2Click = callback)
            3 -> appBarState.value = appBarState.value.copy(onIcon3Click = callback)
        }
    }

    fun setIcons(
        appBarState: MutableState<AppBarState>,
        iconIndex: Int,
        icon: ImageVector
    ) {
        when (iconIndex) {
            1 -> appBarState.value = appBarState.value.copy(icon1 = icon)
            2 -> appBarState.value = appBarState.value.copy(icon2 = icon)
            3 -> appBarState.value = appBarState.value.copy(icon3 = icon)
        }
    }

    fun setOnSearchTextChange(
        appBarState: MutableState<AppBarState>,
        callback: (String) -> Unit
    ) {
        appBarState.value = appBarState.value.copy(onSearchTextChange = callback)
    }
}