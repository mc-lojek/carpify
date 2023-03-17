package pl.mclojek.carpify.presentation.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.google.android.gms.maps.model.LatLng
import pl.mclojek.carpify.domain.model.Fish
import pl.mclojek.carpify.presentation.listitems.FishListItem
import java.time.ZonedDateTime

@Composable
fun MyFishScreen() {
    LazyColumn {
        items(fakeFishList) {
            FishListItem(fish = it)
        }
    }
}

val fakeFishList = listOf(
    Fish(
        "PIERWSZA",
        "Common carp",
        28.70f,
        99,
        ZonedDateTime.now(),
        LatLng(54.098735, 17.608685),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://pariscarpfishing.com/wp-content/uploads/2021/04/Paris-carp-fishing-carpe-commune3.jpg",
        "1"
    ),
    Fish(
        "PIERWSZA",
        "Mirror carp",
        28.70f,
        99,
        ZonedDateTime.now(),
        LatLng(54.098164, 17.613152),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://i.ytimg.com/vi/Alfs_Bv9_50/maxresdefault.jpg",
        "1"
    ),
    Fish(
        "PIERWSZA",
        "Grass carp",
        28.70f,
        99,
        ZonedDateTime.now(),
        LatLng(54.100603, 17.608052),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://cdn.cmc-gallery.pl/static/files/gallery/125/1222007_1632342957.jpg",
        "1"
    ),
    Fish(
        "PIERWSZA",
        "Sturgeon",
        42.0f,
        157,
        ZonedDateTime.now().minusDays(3),
        LatLng(54.100334, 17.610990),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://blog.wedkarski.com/wp-content/uploads/2020/07/zdj-tytulowe.jpg",
        "1"
    ),
)