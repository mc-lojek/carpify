package pl.mclojek.carpify.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkManager {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("192.168.0.123:8000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(RestService::class.java)
}