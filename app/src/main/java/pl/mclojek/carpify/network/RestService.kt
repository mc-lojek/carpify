package pl.mclojek.carpify.network

import pl.mclojek.carpify.domain.model.Lake
import retrofit2.http.GET

interface RestService {

    @GET("/lakes/")
    suspend fun listLakes(): ArrayList<Lake>
}