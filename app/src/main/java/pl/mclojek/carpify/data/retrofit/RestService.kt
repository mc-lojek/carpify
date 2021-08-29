package pl.mclojek.carpify.data.retrofit

import okhttp3.ResponseBody
import pl.mclojek.carpify.data.model.FishDataModel
import pl.mclojek.carpify.data.model.LakeDataModel
import retrofit2.http.GET

interface RestService {

    @GET("fish/")
    suspend fun getAllFish(): List<FishDataModel>

    @GET("lakes/")
    suspend fun getAllLakes(): List<LakeDataModel>
}