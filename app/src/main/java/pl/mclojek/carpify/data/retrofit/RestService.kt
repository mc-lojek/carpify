package pl.mclojek.carpify.data.retrofit

import okhttp3.ResponseBody
import pl.mclojek.carpify.data.model.FishDataModel
import pl.mclojek.carpify.data.model.LakeDataModel
import pl.mclojek.carpify.domain.model.Fish
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RestService {

    @GET("fish/")
    suspend fun getAllFish(): List<FishDataModel>

    @GET("lakes/")
    suspend fun getAllLakes(): List<LakeDataModel>

    @POST("fish/")
    suspend fun sendFish(@Body fish: FishDataModel): String
}