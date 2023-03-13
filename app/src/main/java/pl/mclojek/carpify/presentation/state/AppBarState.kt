package pl.mclojek.carpify.presentation.state

import androidx.compose.ui.graphics.vector.ImageVector

data class AppBarState(
    val title: String = "",
    val backIcon: ImageVector? = null,
    val onBackPressed: (() -> Unit)? = null,
    val icon1: ImageVector? = null,
    val icon2: ImageVector? = null,
    val icon3: ImageVector? = null,
    val onIcon1Click: (() -> Unit)? = null,
    val onIcon2Click: (() -> Unit)? = null,
    val onIcon3Click: (() -> Unit)? = null,
    val searchingMode: Boolean = false,
    val searchText: String = "",
    val onSearchTextChange: ((String) -> Unit)? = null
)