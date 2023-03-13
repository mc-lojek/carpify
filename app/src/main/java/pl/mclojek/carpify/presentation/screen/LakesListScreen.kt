package pl.mclojek.carpify.presentation.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import pl.mclojek.carpify.domain.model.Lake
import pl.mclojek.carpify.presentation.listitems.LakesListItem

@Composable
fun LakesListScreen() {
    LazyColumn() {
        items(items = fakeLakesList) {
            LakesListItem(lake = it,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(128.dp)
            )
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
        name = "Jezioro Pieszczenko",
        description = "Urocze leśne jeziorko :)",
        bounds = LatLngBounds(
            LatLng(53.943281,18.243484),
            LatLng(53.945352,18.249278)
        ),
        imageUrl = "https://bookingfish.eu/wp-content/uploads/304c123a-08f3-4041-8731-da3f7b03a7c7_Easy-Resize.com_.jpg"
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