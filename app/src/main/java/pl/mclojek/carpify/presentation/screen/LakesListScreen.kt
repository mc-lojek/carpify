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
import pl.mclojek.carpify.presentation.screen.ScreenRoutes.FISH_MAP_SCREEN_ROUTE

@Composable
fun LakesListScreen(navCallback: (String) -> Unit) {
    LazyColumn() {
        items(items = fakeLakesList) {
            LakesListItem(
                lake = it,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(128.dp)
            ) {
                navCallback(FISH_MAP_SCREEN_ROUTE)
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
        name = "Jezioro Pieszczenko",
        description = "Urocze leśne jeziorko :)",
        bounds = LatLngBounds(
            LatLng(53.943281,18.243484),
            LatLng(53.945352,18.249278)
        ),
        imageUrl = "https://bookingfish.eu/wp-content/uploads/304c123a-08f3-4041-8731-da3f7b03a7c7_Easy-Resize.com_.jpg"
    ),
    Lake(
        name = "Łowisko Brzeżonko",
        description = "Klubowa woda niedaleko trójmiasta",
        bounds = LatLngBounds(
            LatLng(54.454144, 18.260083),
            LatLng(54.458602, 18.263716)
        ),
        imageUrl = "https://scontent-waw1-1.xx.fbcdn.net/v/t39.30808-6/302433835_491012646363585_1319400280667307609_n.png?_nc_cat=104&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=DHYIfO0ZlrMAX-z_M7N&_nc_ht=scontent-waw1-1.xx&oh=00_AfB5WP9EK0yl8U-xzUvfXGkV5Lfq2CF7zJS6exEvXQ5e5g&oe=6418B3BE"
    ),
    Lake(
        name = "Łowisko Pogalewo",
        description = "Fajne łowisko z domkami",
        bounds = LatLngBounds(
            LatLng(51.251289, 16.631011),
            LatLng(51.253313, 16.634183)
        ),
        imageUrl = "https://i.ytimg.com/vi/BIo9n4gJkuI/maxresdefault.jpg"
    ),
    Lake(
        name = "Łowisko Kłodzionko",
        description = "Fajna sportowa woda pod feederka",
        bounds = LatLngBounds(
            LatLng(54.207595, 17.751983),
            LatLng(54.210839, 17.757841)
        ),
        imageUrl = "https://scontent-waw1-1.xx.fbcdn.net/v/t39.30808-6/299482652_487178016740767_4660327603837742730_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=vHAwtLuPgdEAX_hduPL&_nc_ht=scontent-waw1-1.xx&oh=00_AfC4fJ4xH2rK9rnQ7RixscTUyzkYyENBJkUhE3tyoFN-sA&oe=6418FDC8"
    ),
)