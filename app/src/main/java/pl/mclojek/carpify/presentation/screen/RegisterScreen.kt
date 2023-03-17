package pl.mclojek.carpify.presentation.screen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.mclojek.carpify.presentation.screen.ScreenRoutes.BACK

@Composable
fun RegisterScreen(navigateCallback: (String) -> Unit) {
    Button(modifier = Modifier, onClick = {
        navigateCallback(BACK)
    }) {
        Text("Register")
    }
}