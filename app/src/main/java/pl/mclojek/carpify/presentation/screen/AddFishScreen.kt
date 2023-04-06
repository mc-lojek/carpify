package pl.mclojek.carpify.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.mclojek.carpify.presentation.screen.ScreenRoutes.BACK

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFishScreen(navCallback: (String) -> Unit) {


    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Text(text = "Add fish")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navCallback(BACK)
                        }
                    ) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                },
                actions = {
                    IconButton(
                        onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Done, null)
                    }
                },
            )
        }, content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                Text("TU BEDZIE DODAWANIE NOWEJ RYBKI HYH")
            }
        })
}