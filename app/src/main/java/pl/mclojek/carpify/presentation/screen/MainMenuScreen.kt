package pl.mclojek.carpify.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import pl.mclojek.carpify.presentation.screen.ScreenRoutes.LAKES_LIST_SCREEN_ROUTE
import pl.mclojek.carpify.presentation.screen.ScreenRoutes.LAKES_MAP_SCREEN_ROUTE

@Composable
fun MainMenuScreen(
    navCallback: (String) -> Unit
) {
    Column() {
        Button(onClick = { navCallback(LAKES_LIST_SCREEN_ROUTE) }) {
            Text("Lakes List")
        }
        Button(onClick = { navCallback(LAKES_MAP_SCREEN_ROUTE) }) {
            Text("Lakes Map")
        }
    }
}