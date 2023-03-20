package pl.mclojek.carpify.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.mclojek.carpify.presentation.screen.destinations.ForgotPasswordScreenDestination
import pl.mclojek.carpify.presentation.screen.destinations.MainMenuScreenDestination
import pl.mclojek.carpify.presentation.screen.destinations.RegisterScreenDestination

@Composable
@Destination(start = true)
fun LoginScreen(
    navigator: DestinationsNavigator
) {
    Column() {
        Button(modifier = Modifier, onClick = { navigator.navigate(MainMenuScreenDestination()) }) {
            Text("Login")
        }
        Button(modifier = Modifier, onClick = { navigator.navigate(RegisterScreenDestination()) }) {
            Text("Register")
        }
        Button(
            modifier = Modifier,
            onClick = { navigator.navigate(ForgotPasswordScreenDestination()) }) {
            Text("Forget")
        }
    }
}