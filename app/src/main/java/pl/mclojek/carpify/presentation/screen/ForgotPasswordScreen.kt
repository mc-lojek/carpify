package pl.mclojek.carpify.presentation.screen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ForgotPasswordScreen(navigateBack: () -> Unit) {
    Button(modifier = Modifier, onClick = navigateBack) {
        Text("Forget")
    }
}