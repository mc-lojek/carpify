package pl.mclojek.carpify.presentation.screen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.google.android.gms.maps.model.LatLng
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.ResultBackNavigator

@Destination
@Composable
fun CoordsSelectionScreen(resultNavigator: ResultBackNavigator<LatLng>) {

    Button(
        onClick = { resultNavigator.navigateBack(LatLng(54.0, 18.0)) }
    ) {
        Text("Save")
    }

}