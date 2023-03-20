package pl.mclojek.carpify.presentation.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun FeedScreen(
    navigator: DestinationsNavigator
) {
    Text("FeedScreen")
}