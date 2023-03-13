package pl.mclojek.carpify.presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.domain.model.POLAND_BOUNDS
import pl.mclojek.carpify.domain.model.POLAND_ZOOM
import pl.mclojek.carpify.presentation.listitems.LakesListItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LakesMapScreen() {

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

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

    Box(modifier = Modifier.fillMaxSize()) {
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
            )
        }
    }
}