package pl.mclojek.carpify.network

import pl.mclojek.carpify.data.Lake
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET

interface RestService {

    @GET("lakes")
    fun listLakes(): Call<List<Lake>>
}