package pl.mclojek.carpify.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.domain.model.POLAND_BOUNDS
import pl.mclojek.carpify.domain.model.POLAND_ZOOM
import pl.mclojek.carpify.domain.model.fakeLakesList
import pl.mclojek.carpify.presentation.components.SearchField
import pl.mclojek.carpify.presentation.listitems.LakesListItem
import pl.mclojek.carpify.presentation.screen.destinations.FishMapScreenDestination
import pl.mclojek.carpify.presentation.state.AppBarController
import pl.mclojek.carpify.presentation.state.AppBarState

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
@Destination
fun LakesMapScreen(navigator: DestinationsNavigator) {

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    val keyboardController = LocalSoftwareKeyboardController.current

    val appBarState = remember {
        mutableStateOf(AppBarState(title = "Łowiska"))
    }

    val cameraPositionState: CameraPositionState = rememberCameraPositionState(init = {
        position = CameraPosition(
            POLAND_BOUNDS.center,
            POLAND_ZOOM,
            0f,
            0f
        )
    })

    LaunchedEffect(pagerState.settledPage) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newCameraPosition(
                CameraPosition(
                    fakeLakesList[pagerState.settledPage].bounds.center,
                    10f,
                    0f,
                    0f
                )
            ),
            durationMs = 1000
        )
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
        }, content = {
            Box(modifier = Modifier
                .padding(it)
                .fillMaxSize()) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState
                ) {
                    fakeLakesList.forEach {
                        Marker(
                            state = MarkerState(it.bounds.center),
                            title = it.name,
                            tag = it,
                            onClick = { marker ->
                                coroutineScope.launch {
                                    pagerState.scrollToPage(fakeLakesList.indexOf(marker.tag as Lake))
                                }
                                false
                            }
                        )
                    }
                }
                HorizontalPager(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    state = pagerState,
                    pageCount = fakeLakesList.size,
                    beyondBoundsPageCount = 1,
                    contentPadding = PaddingValues(horizontal = 32.dp),
                    pageSize = PageSize.Fill
                ) { page ->
                    LakesListItem(
                        lake = fakeLakesList[page],
                        modifier = Modifier
                            .height(128.dp)
                            .padding(horizontal = 16.dp)
                    ) {
                        navigator.navigate(FishMapScreenDestination(it.id))
                    }
                }
            }
        })


}