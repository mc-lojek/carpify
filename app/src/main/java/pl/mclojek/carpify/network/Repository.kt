package pl.mclojek.carpify.network

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.mclojek.carpify.data.Lake
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

object Repository {

    //val manager = NetworkManager()

    suspend fun getAllLakes() = withContext(Dispatchers.IO)
    {
        return@withContext try {
            //val response = manager.service.listLakes()
            var response = ArrayList<Lake>()
            response.add(Lake(1, "Miłoszewo", "Pomorskie", "123.123456, 123.123456;55.654321,77.123456"))
            response.add(Lake(1, "Miłoszewo", "Pomorskie", "123.123456, 123.123456;55.654321,77.123456"))
            response.add(Lake(1, "Miłoszewo", "Pomorskie", "123.123456, 123.123456;55.654321,77.123456"))
            response.add(Lake(1, "Miłoszewo", "Pomorskie", "123.123456, 123.123456;55.654321,77.123456"))
            response.add(Lake(1, "Miłoszewo", "Pomorskie", "123.123456, 123.123456;55.654321,77.123456"))
            response.add(Lake(1, "Miłoszewo", "Pomorskie", "123.123456, 123.123456;55.654321,77.123456"))
            response.add(Lake(1, "Miłoszewo", "Pomorskie", "123.123456, 123.123456;55.654321,77.123456"))
            response.add(Lake(1, "Miłoszewo", "Pomorskie", "123.123456, 123.123456;55.654321,77.123456"))
            response.add(Lake(1, "Miłoszewo", "Pomorskie", "123.123456, 123.123456;55.654321,77.123456"))
            Timber.d("Successful")
            NetworkResponse(response, true)
        } catch (e: Exception) {
            e.printStackTrace()
            var message = "DEFAULT_MESSAGE" //TODO: zmienic to
            if (e is HttpException) {
                message = e.message()
            }
            Timber.e("NOT SUCCESSFUL")
            NetworkResponse(null, false, message)
        }
    }

}