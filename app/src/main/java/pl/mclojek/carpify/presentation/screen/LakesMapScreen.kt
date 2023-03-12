package pl.mclojek.carpify.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import pl.mclojek.carpify.domain.model.POLAND_BOUNDS
import pl.mclojek.carpify.domain.model.POLAND_ZOOM

@Composable
fun LakesMapScreen() {
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = CameraPositionState(
            position = CameraPosition(
                POLAND_BOUNDS.center,
                POLAND_ZOOM,
                0f,
                0f
            )
        )
    ) {
        fakeLakesList.forEach {
            Marker(state = MarkerState(it.bounds.center), title = it.name)
        }
    }
}