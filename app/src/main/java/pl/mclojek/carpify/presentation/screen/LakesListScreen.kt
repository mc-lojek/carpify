package pl.mclojek.carpify.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.presentation.theme.Typography

@Composable
fun LakesListScreen() {
    LazyColumn() {
        items(items = fakeLakesList) {
            LakesListItem(lake = it)
        }
    }
}

@Composable
fun LakesListItem(lake: Lake) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(128.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row() {
            AsyncImage(
                model = lake.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxHeight().aspectRatio(1.0f),
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(color = Color.DarkGray),
            )
            Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Text(text = lake.name, style = Typography.headlineSmall)
                Text(text = lake.description, style = Typography.bodySmall)
            }
        }
    }
}

val fakeLakesList = listOf(
    Lake(
        name = "Jezioro Miłoszewskie",
        description = "Jeziorko z wielkimi karpiami",
        bounds = LatLngBounds(
            LatLng(54.442059, 18.034701),
            LatLng(54.449607, 18.048584)
        ),
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSkuApOFqn3yqGfWeQADKNxDsLLL0vInVmkUA&usqp=CAU"
    ),
    Lake(
        name = "Jezioro Krążno",
        description = "Polska Gigantica",
        bounds = LatLngBounds(
            LatLng(54.096865,17.605816),
            LatLng(54.101552,17.613820)
        ),
        imageUrl = "https://karpiowymtropem.pl/wp-content/uploads/2022/09/5..-1024x768.jpg"
    ),
    Lake(
        name = "Jezioro Miłoszewskie",
        description = "Jeziorko z wielkimi karpiami",
        bounds = LatLngBounds(
            LatLng(54.442059, 18.034701),
            LatLng(54.449607, 18.048584)
        ),
        imageUrl = "https://www.karpmax.pl/blog_pliki/772_p1030565.jpg"
    ),
    Lake(
        name = "Jezioro Miłoszewskie",
        description = "Jeziorko z wielkimi karpiami",
        bounds = LatLngBounds(
            LatLng(54.442059, 18.034701),
            LatLng(54.449607, 18.048584)
        ),
        imageUrl = "http://miloszewskie.pl/wp-content/uploads/2014/02/IMG_6747.jpg"
    ),
    Lake(
        name = "Jezioro Miłoszewskie",
        description = "Jeziorko z wielkimi karpiami",
        bounds = LatLngBounds(
            LatLng(54.442059, 18.034701),
            LatLng(54.449607, 18.048584)
        ),
        imageUrl = "http://miloszewskie.pl/wp-content/uploads/2014/02/IMG_6747.jpg"
    ),
    Lake(
        name = "Jezioro Miłoszewskie",
        description = "Jeziorko z wielkimi karpiami",
        bounds = LatLngBounds(
            LatLng(54.442059, 18.034701),
            LatLng(54.449607, 18.048584)
        ),
        imageUrl = "http://miloszewskie.pl/wp-content/uploads/2014/02/IMG_6747.jpg"
    ),
)