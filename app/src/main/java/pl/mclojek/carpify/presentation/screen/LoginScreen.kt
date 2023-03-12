package pl.mclojek.carpify.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoginScreen(navigateForward: () -> Unit, navigateRegister: () -> Unit, navigateForgetPass: () -> Unit, ) {
    Column() {
        Button(modifier = Modifier, onClick = navigateForward) {
            Text("Login")
        }
        Button(modifier = Modifier, onClick = navigateRegister) {
            Text("Register")
        }
        Button(modifier = Modifier, onClick = navigateForgetPass) {
            Text("Forget")
        }
    }
}