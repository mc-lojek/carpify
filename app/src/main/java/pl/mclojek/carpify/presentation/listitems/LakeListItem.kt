package pl.mclojek.carpify.presentation.listitems

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.presentation.theme.Typography

@Composable
fun LakesListItem(lake: Lake, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row() {
            AsyncImage(
                model = lake.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1.0f),
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(color = Color.DarkGray),
            )
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
                Text(text = lake.name, style = Typography.headlineSmall)
                Text(text = lake.description, style = Typography.bodySmall)
            }
        }
    }
}