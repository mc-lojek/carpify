package pl.mclojek.carpify.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.mclojek.carpify.domain.extensions.toDatetimeReadable
import pl.mclojek.carpify.domain.extensions.toReadable
import pl.mclojek.carpify.domain.model.fakeFishList
import pl.mclojek.carpify.presentation.components.SearchField
import pl.mclojek.carpify.presentation.components.TitledInputItem
import pl.mclojek.carpify.presentation.screen.destinations.FishDetailsScreenDestination
import pl.mclojek.carpify.presentation.state.AppBarController
import pl.mclojek.carpify.presentation.state.AppBarState

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
@Destination
fun FishDetailsScreen(navigator: DestinationsNavigator, fishId: String) {

    val keyboardController = LocalSoftwareKeyboardController.current

    val appBarState = remember {
        mutableStateOf(AppBarState(title = fishId))
    }

    val fish = fakeFishList.first { it.id == fishId }

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
                                    navigator.navigate(FishDetailsScreenDestination("FOO"))
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
            Column(Modifier.padding(padding)) {
                AsyncImage(
                    model = fish.photoUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
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
        }
    )
}