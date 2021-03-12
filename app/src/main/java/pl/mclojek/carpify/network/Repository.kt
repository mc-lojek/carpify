package pl.mclojek.carpify.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.mclojek.carpify.domain.model.Lake
import retrofit2.HttpException
import timber.log.Timber

object Repository {

    //val manager = NetworkManager()

    suspend fun getAllLakes() = withContext(Dispatchers.IO)
    {
        return@withContext try {
            //val response = manager.service.listLakes()
            var response = ArrayList<Lake>()
            response.add(
                Lake(
                    1,
                    "Miłoszewo",
                    "Pomorskie",
                    "123.123456, 123.123456;55.654321,77.123456"
                )
            )
            response.add(
                Lake(
                    1,
                    "Miłoszewo",
                    "Pomorskie",
                    "123.123456, 123.123456;55.654321,77.123456"
                )
            )
            response.add(
                Lake(
                    1,
                    "Miłoszewo",
                    "Pomorskie",
                    "123.123456, 123.123456;55.654321,77.123456"
                )
            )
            response.add(
                Lake(
                    1,
                    "Miłoszewo",
                    "Pomorskie",
                    "123.123456, 123.123456;55.654321,77.123456"
                )
            )
            response.add(
                Lake(
                    1,
                    "Miłoszewo",
                    "Pomorskie",
                    "123.123456, 123.123456;55.654321,77.123456"
                )
            )
            response.add(
                Lake(
                    1,
                    "Miłoszewo",
                    "Pomorskie",
                    "123.123456, 123.123456;55.654321,77.123456"
                )
            )
            response.add(
                Lake(
                    1,
                    "Miłoszewo",
                    "Pomorskie",
                    "123.123456, 123.123456;55.654321,77.123456"
                )
            )
            response.add(
                Lake(
                    1,
                    "Miłoszewo",
                    "Pomorskie",
                    "123.123456, 123.123456;55.654321,77.123456"
                )
            )
            response.add(
                Lake(
                    1,
                    "Miłoszewo",
                    "Pomorskie",
                    "123.123456, 123.123456;55.654321,77.123456"
                )
            )
            Timber.d("Successful")
            //NetworkResponse(response, true)
        } catch (e: Exception) {
            e.printStackTrace()
            var message = "DEFAULT_MESSAGE" //TODO: zmienic to
            if (e is HttpException) {
                message = e.message()
            }
            Timber.e("NOT SUCCESSFUL")
            //NetworkResponse(null, false, message)
        }
    }

}