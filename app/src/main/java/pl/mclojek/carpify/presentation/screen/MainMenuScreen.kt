package pl.mclojek.carpify.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.mclojek.carpify.presentation.screen.ScreenRoutes.LAKES_LIST_SCREEN_ROUTE
import pl.mclojek.carpify.presentation.screen.ScreenRoutes.LAKES_MAP_SCREEN_ROUTE
import pl.mclojek.carpify.presentation.screen.ScreenRoutes.MY_FISH_SCREEN_ROUTE
import pl.mclojek.carpify.presentation.screen.destinations.LakesListScreenDestination
import pl.mclojek.carpify.presentation.screen.destinations.LakesMapScreenDestination
import pl.mclojek.carpify.presentation.screen.destinations.MyFishScreenDestination

@Composable
@Destination
fun MainMenuScreen(
    navigator: DestinationsNavigator
) {
    Column() {
        Button(onClick = { navigator.navigate(LakesListScreenDestination()) }) {
            Text("Lakes List")
        }
        Button(onClick = { navigator.navigate(LakesMapScreenDestination()) }) {
            Text("Lakes Map")
        }
        Button(onClick = { navigator.navigate(MyFishScreenDestination()) }) {
            Text("My fish")
        }
    }
}