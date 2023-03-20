package pl.mclojek.carpify.presentation.screen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.mclojek.carpify.presentation.screen.ScreenRoutes.BACK

@Composable
@Destination
fun ForgotPasswordScreen(navigator: DestinationsNavigator) {
    Button(modifier = Modifier, onClick = {

    }) {
        Text("Forget")
    }
}