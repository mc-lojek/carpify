package pl.mclojek.carpify.network

import retrofit2.Retrofit

object NetworkManager {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("192.168.0.123:8000")
        .build()

    val service:
}