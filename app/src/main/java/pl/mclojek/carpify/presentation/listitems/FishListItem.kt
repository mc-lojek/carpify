package pl.mclojek.carpify.presentation.listitems

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pl.mclojek.carpify.domain.extensions.toDatetimeReadable
import pl.mclojek.carpify.domain.model.Fish

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FishListItem(fish: Fish, onClick: (Fish) -> Unit) {
    Card(
        onClick = { onClick(fish) },
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text("${fish.species.name} ${fish.weight} kg")
            Spacer(Modifier.width(4.dp))
            Text(fish.catchDatetime.toDatetimeReadable())
        }
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = fish.photoUrl,
            contentDescription = null
        )
        Text(fish.description)
    }
}