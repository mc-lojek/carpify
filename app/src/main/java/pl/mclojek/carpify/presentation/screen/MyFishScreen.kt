package pl.mclojek.carpify.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.google.android.gms.maps.model.LatLng
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.presentation.components.SearchField
import pl.mclojek.carpify.presentation.listitems.FishListItem
import pl.mclojek.carpify.presentation.screen.ScreenRoutes.BACK
import pl.mclojek.carpify.presentation.screen.ScreenRoutes.FISH_DETAILS_SCREEN
import pl.mclojek.carpify.presentation.state.AppBarController
import pl.mclojek.carpify.presentation.state.AppBarState
import java.time.ZonedDateTime

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MyFishScreen(navCallback: (String) -> Unit) {

    val keyboardController = LocalSoftwareKeyboardController.current

    val appBarState = remember {
        mutableStateOf(AppBarState(title = "<Lake>"))
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Text(text = appBarState.value.title)
                },
                navigationIcon = {
                    AnimatedVisibility(visible = !appBarState.value.searchingMode) {
                        IconButton(
                            onClick = {
                                if (appBarState.value.searchingMode) {
                                    AppBarController.changeToDefaultMode(appBarState)
                                } else {
                                    navCallback(BACK)
                                }
                            }
                        ) {
                            Icon(Icons.Filled.ArrowBack, null)
                        }
                    }
                },
                actions = {
                    AnimatedVisibility(
                        visible = appBarState.value.searchingMode
                    ) {
                        SearchField(
                            searchText = appBarState.value.searchText,
                            keyboardController = keyboardController,
                            onHideClicked = { AppBarController.changeToDefaultMode(appBarState) }
                        ) { AppBarController.updateSearchText(appBarState, it) }
                    }
                    IconButton(
                        onClick = { AppBarController.changeToSearchMode(appBarState) }) {
                        Icon(Icons.Filled.Search, null)
                    }
                    IconButton(
                        onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Send, null)
                    }
                    IconButton(
                        onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.MoreVert, null)
                    }
                },
            )
        }, content = { padding ->
            LazyColumn(Modifier.padding(padding)) {
                items(fakeFishList) {
                    FishListItem(fish = it) {
                        navCallback(FISH_DETAILS_SCREEN)
                    }
                }
            }
        }
    )
}

val fakeFishList = listOf(
    Fish(
        "PIERWSZA",
        "Common carp",
        28.70f,
        99,
        ZonedDateTime.now(),
        LatLng(54.098735, 17.608685),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://pariscarpfishing.com/wp-content/uploads/2021/04/Paris-carp-fishing-carpe-commune3.jpg",
        "1"
    ),
    Fish(
        "PIERWSZA",
        "Mirror carp",
        28.70f,
        99,
        ZonedDateTime.now(),
        LatLng(54.098164, 17.613152),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://i.ytimg.com/vi/Alfs_Bv9_50/maxresdefault.jpg",
        "1"
    ),
    Fish(
        "PIERWSZA",
        "Grass carp",
        28.70f,
        99,
        ZonedDateTime.now(),
        LatLng(54.100603, 17.608052),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://cdn.cmc-gallery.pl/static/files/gallery/125/1222007_1632342957.jpg",
        "1"
    ),
    Fish(
        "PIERWSZA",
        "Sturgeon",
        42.0f,
        157,
        ZonedDateTime.now().minusDays(3),
        LatLng(54.100334, 17.610990),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://blog.wedkarski.com/wp-content/uploads/2020/07/zdj-tytulowe.jpg",
        "1"
    ),
)