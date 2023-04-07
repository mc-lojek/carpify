package pl.mclojek.carpify.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import pl.mclojek.carpify.R
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.domain.model.fakeLakesList
import pl.mclojek.carpify.presentation.bitmapDescriptorFromVector
import pl.mclojek.carpify.presentation.components.SearchField
import pl.mclojek.carpify.presentation.listitems.FishListItem
import pl.mclojek.carpify.presentation.screen.destinations.AddFishScreenDestination
import pl.mclojek.carpify.presentation.screen.destinations.FishDetailsScreenDestination
import pl.mclojek.carpify.presentation.state.AppBarController
import pl.mclojek.carpify.presentation.state.AppBarState
import pl.mclojek.carpify.presentation.viewmodel.FishMapViewModel

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
@Destination
fun FishMapScreen(
    navigator: DestinationsNavigator,
    viewModel: FishMapViewModel = hiltViewModel(),
    lakeId: String,
) {

    LaunchedEffect(true) {
        viewModel.load(lakeId)
    }

    val state = viewModel.state

    val lake = fakeLakesList.first { it.id == lakeId }

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    val keyboardController = LocalSoftwareKeyboardController.current

    val appBarState = remember {
        mutableStateOf(AppBarState(title = lake.name))
    }

    val infiniteTransition = rememberInfiniteTransition()
    val anchor by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val mapUiSettings = MapUiSettings(
        compassEnabled = true,
        indoorLevelPickerEnabled = false,
        mapToolbarEnabled = false,
        myLocationButtonEnabled = false,
        rotationGesturesEnabled = false,
        scrollGesturesEnabled = true,
        scrollGesturesEnabledDuringRotateOrZoom = true,
        tiltGesturesEnabled = true,
        zoomControlsEnabled = false,
        zoomGesturesEnabled = true
    )

    val cameraPositionState: CameraPositionState = rememberCameraPositionState(init = {
        position = CameraPosition(
            lake.bounds.center,
            16f,
            0f,
            0f
        )
    })

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
                        onClick = { navigator.navigate(AddFishScreenDestination) }) {
                        Icon(Icons.Filled.AddCircle, null)
                    }
                    IconButton(
                        onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.MoreVert, null)
                    }
                },
            )
        }, content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    uiSettings = mapUiSettings,
                    properties = MapProperties(mapType = MapType.SATELLITE)
                ) {
                    state.fishList.forEachIndexed { index, item ->
                        Marker(
                            icon = bitmapDescriptorFromVector(
                                LocalContext.current,
                                R.drawable.fish
                            ),
                            anchor = if (pagerState.currentPage == index) Offset(
                                0.5f,
                                anchor
                            ) else Offset(0.5f, 1f),
                            state = MarkerState(item.catchPosition),
                            title = "${item.species} + ${item.weight} kg",
                            tag = item,
                            onClick = { marker ->
                                coroutineScope.launch {
                                    pagerState.scrollToPage(state.fishList.indexOf(marker.tag as Fish))
                                }
                                true
                            }
                        )
                    }
                }
                AnimatedVisibility(
                    visible = !state.isLoading,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                ) {
                    HorizontalPager(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .padding(vertical = 32.dp),
                        state = pagerState,
                        pageCount = state.fishList.size,
                        beyondBoundsPageCount = 1,
                        contentPadding = PaddingValues(horizontal = 32.dp),
                        pageSize = PageSize.Fill
                    ) { page ->
                        FishListItem(fish = state.fishList[page]) {
                            navigator.navigate(FishDetailsScreenDestination(it.id))
                        }
                    }
                }
                AnimatedVisibility(
                    modifier = Modifier.align(Alignment.Center),
                    visible = state.isLoading,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.White.copy(alpha = 0.5f)),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier)
                    }
                }
            }
        })
}