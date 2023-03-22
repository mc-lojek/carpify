package pl.mclojek.carpify.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.mclojek.carpify.domain.extensions.toDatetimeReadable
import pl.mclojek.carpify.domain.extensions.toReadable
import pl.mclojek.carpify.domain.model.fakeFishList
import pl.mclojek.carpify.presentation.components.SearchField
import pl.mclojek.carpify.presentation.components.TitledInputItem
import pl.mclojek.carpify.presentation.components.ZoomableImage
import pl.mclojek.carpify.presentation.state.AppBarController
import pl.mclojek.carpify.presentation.state.AppBarState

@OptIn(
    ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
@Destination
fun FishDetailsScreen(navigator: DestinationsNavigator, fishId: String) {

    val keyboardController = LocalSoftwareKeyboardController.current


    var showFullScreenImage by remember {
        mutableStateOf(false)
    }

    val fish = fakeFishList.first { it.id == fishId }


    val appBarState = remember {
        mutableStateOf(AppBarState(title = fish.species))
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
                                    navigator.navigateUp()
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

            Box(
                Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                Column() {
                    AsyncImage(
                        model = fish.photoUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { showFullScreenImage = true },
                    )
                    TitledInputItem(title = "Gatunek", inputText = fish.species)
                    TitledInputItem(title = "Waga", inputText = "${fish.weight.toReadable(3)} kg")
                    TitledInputItem(title = "Długość", inputText = "${fish.length} cm")
                    TitledInputItem(
                        title = "Data złapania",
                        inputText = fish.catchDatetime.toDatetimeReadable()
                    )
                    TitledInputItem(title = "Opis", inputText = fish.description)
                }

                if (showFullScreenImage) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.7f))
                    )
                    {
                        ZoomableImage(
                            model = fish.photoUrl,
                            modifier = Modifier.fillMaxSize(),
                            onClose = { showFullScreenImage = false }
                        )
                    }
                }
            }
        }
    )
}