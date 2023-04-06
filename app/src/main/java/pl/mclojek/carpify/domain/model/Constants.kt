package pl.mclojek.carpify.domain.model

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import java.time.ZonedDateTime

val POLAND_BOUNDS = LatLngBounds(
    LatLng(48.821333, 14.018555),
    LatLng(55.229023, 24.499512)
)

val POLAND_ZOOM = 5.7f

val fakeFishList = mutableListOf(
    Fish(
        "1",
        "Common carp",
        28.70f,
        99,
        ZonedDateTime.now(),
        LatLng(54.446282, 18.041470),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://pariscarpfishing.com/wp-content/uploads/2021/04/Paris-carp-fishing-carpe-commune3.jpg",
        "1"
    ),
    Fish(
        "2",
        "Mirror carp",
        28.70f,
        99,
        ZonedDateTime.now(),
        LatLng(54.445909, 18.045284),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://i.ytimg.com/vi/Alfs_Bv9_50/maxresdefault.jpg",
        "1"
    ),
    Fish(
        "3",
        "Grass carp",
        28.70f,
        99,
        ZonedDateTime.now(),
        LatLng(54.443641, 18.041642),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://cdn.cmc-gallery.pl/static/files/gallery/125/1222007_1632342957.jpg",
        "1"
    ),
    Fish(
        "4",
        "Sturgeon",
        42.0f,
        157,
        ZonedDateTime.now().minusDays(3),
        LatLng(54.446486, 18.040185),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://blog.wedkarski.com/wp-content/uploads/2020/07/zdj-tytulowe.jpg",
        "1"
    ),

    Fish(
        "1",
        "Common carp",
        28.70f,
        99,
        ZonedDateTime.now(),
        LatLng(54.098735, 17.608685),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://pariscarpfishing.com/wp-content/uploads/2021/04/Paris-carp-fishing-carpe-commune3.jpg",
        "2"
    ),
    Fish(
        "2",
        "Mirror carp",
        28.70f,
        99,
        ZonedDateTime.now(),
        LatLng(54.098164, 17.613152),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://i.ytimg.com/vi/Alfs_Bv9_50/maxresdefault.jpg",
        "2"
    ),
    Fish(
        "3",
        "Grass carp",
        28.70f,
        99,
        ZonedDateTime.now(),
        LatLng(54.100603, 17.608052),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://cdn.cmc-gallery.pl/static/files/gallery/125/1222007_1632342957.jpg",
        "2"
    ),
    Fish(
        "4",
        "Sturgeon",
        42.0f,
        157,
        ZonedDateTime.now().minusDays(3),
        LatLng(54.100334, 17.610990),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://blog.wedkarski.com/wp-content/uploads/2020/07/zdj-tytulowe.jpg",
        "2"
    ),

    Fish(
        "1",
        "Common carp",
        28.70f,
        99,
        ZonedDateTime.now(),
        LatLng(53.944114, 18.248021),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://pariscarpfishing.com/wp-content/uploads/2021/04/Paris-carp-fishing-carpe-commune3.jpg",
        "3"
    ),
    Fish(
        "2",
        "Mirror carp",
        28.70f,
        99,
        ZonedDateTime.now(),
        LatLng(53.943898, 18.247295),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://i.ytimg.com/vi/Alfs_Bv9_50/maxresdefault.jpg",
        "3"
    ),
    Fish(
        "3",
        "Grass carp",
        28.70f,
        99,
        ZonedDateTime.now(),
        LatLng(53.944364, 18.246769),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://cdn.cmc-gallery.pl/static/files/gallery/125/1222007_1632342957.jpg",
        "3"
    ),
    Fish(
        "4",
        "Sturgeon",
        42.0f,
        157,
        ZonedDateTime.now().minusDays(3),
        LatLng(53.944418, 18.244916),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://blog.wedkarski.com/wp-content/uploads/2020/07/zdj-tytulowe.jpg",
        "3"
    ),

    Fish(
        "1",
        "Common carp",
        28.70f,
        99,
        ZonedDateTime.now(),
        LatLng(54.455781, 18.261513),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://pariscarpfishing.com/wp-content/uploads/2021/04/Paris-carp-fishing-carpe-commune3.jpg",
        "4"
    ),
    Fish(
        "2",
        "Mirror carp",
        28.70f,
        99,
        ZonedDateTime.now(),
        LatLng(54.455531, 18.262101),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://i.ytimg.com/vi/Alfs_Bv9_50/maxresdefault.jpg",
        "4"
    ),
    Fish(
        "3",
        "Grass carp",
        28.70f,
        99,
        ZonedDateTime.now(),
        LatLng(54.455233, 18.260840),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://cdn.cmc-gallery.pl/static/files/gallery/125/1222007_1632342957.jpg",
        "4"
    ),
    Fish(
        "4",
        "Sturgeon",
        42.0f,
        157,
        ZonedDateTime.now().minusDays(3),
        LatLng(54.455360, 18.263040),
        "OPIS RANDOMOWY TOUTAJ SOBIE UEJST JAKIS DLUGI OGOLNIE",
        "https://blog.wedkarski.com/wp-content/uploads/2020/07/zdj-tytulowe.jpg",
        "4"
    ),
)


val fakeLakesList = listOf(
    Lake(
        id= "1",
        name = "Jezioro Miłoszewskie",
        description = "Jeziorko z wielkimi karpiami",
        bounds = LatLngBounds(
            LatLng(54.442059, 18.034701),
            LatLng(54.449607, 18.048584)
        ),
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSkuApOFqn3yqGfWeQADKNxDsLLL0vInVmkUA&usqp=CAU"
    ),
    Lake(
        id= "2",
        name = "Jezioro Krążno",
        description = "Polska Gigantica",
        bounds = LatLngBounds(
            LatLng(54.096865,17.605816),
            LatLng(54.101552,17.613820)
        ),
        imageUrl = "https://karpiowymtropem.pl/wp-content/uploads/2022/09/5..-1024x768.jpg"
    ),
    Lake(
        id= "3",
        name = "Jezioro Pieszczenko",
        description = "Urocze leśne jeziorko :)",
        bounds = LatLngBounds(
            LatLng(53.943281,18.243484),
            LatLng(53.945352,18.249278)
        ),
        imageUrl = "https://bookingfish.eu/wp-content/uploads/304c123a-08f3-4041-8731-da3f7b03a7c7_Easy-Resize.com_.jpg"
    ),
    Lake(
        id= "4",
        name = "Łowisko Brzeżonko",
        description = "Klubowa woda niedaleko trójmiasta",
        bounds = LatLngBounds(
            LatLng(54.454144, 18.260083),
            LatLng(54.458602, 18.263716)
        ),
        imageUrl = "https://scontent-waw1-1.xx.fbcdn.net/v/t39.30808-6/302433835_491012646363585_1319400280667307609_n.png?_nc_cat=104&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=DHYIfO0ZlrMAX-z_M7N&_nc_ht=scontent-waw1-1.xx&oh=00_AfB5WP9EK0yl8U-xzUvfXGkV5Lfq2CF7zJS6exEvXQ5e5g&oe=6418B3BE"
    ),
    Lake(
        id= "5",
        name = "Łowisko Pogalewo",
        description = "Fajne łowisko z domkami",
        bounds = LatLngBounds(
            LatLng(51.251289, 16.631011),
            LatLng(51.253313, 16.634183)
        ),
        imageUrl = "https://i.ytimg.com/vi/BIo9n4gJkuI/maxresdefault.jpg"
    ),
    Lake(
        id= "6",
        name = "Łowisko Kłodzionko",
        description = "Fajna sportowa woda pod feederka",
        bounds = LatLngBounds(
            LatLng(54.207595, 17.751983),
            LatLng(54.210839, 17.757841)
        ),
        imageUrl = "https://scontent-waw1-1.xx.fbcdn.net/v/t39.30808-6/299482652_487178016740767_4660327603837742730_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=vHAwtLuPgdEAX_hduPL&_nc_ht=scontent-waw1-1.xx&oh=00_AfC4fJ4xH2rK9rnQ7RixscTUyzkYyENBJkUhE3tyoFN-sA&oe=6418FDC8"
    ),
)