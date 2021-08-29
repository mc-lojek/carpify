package pl.mclojek.carpify.network

import org.json.JSONObject
import retrofit2.HttpException
import timber.log.Timber
import java.net.SocketTimeoutException

object NetworkErrorHandler {

    fun <T : Any> handleException(t: Throwable): ApiResponse<T> {
        return when (t) {
            is HttpException -> ApiResponse.Error(handleHttpException(t))
            is SocketTimeoutException -> ApiResponse.Error(handleSocketTimeoutException(t))
            else -> ApiResponse.Error(t.message ?: "(1) Undefined Error Occured, contact developers")
        }
    }

    private fun handleSocketTimeoutException(ex: SocketTimeoutException): String {
        Timber.tag("FOO").d(ex.message.toString())
        return ex.message.toString()
    }

    private fun handleHttpException(ex: HttpException): String {
        val jsonObject = JSONObject(
            ex.response()?.errorBody()?.string()
                ?: "{\"detail\": \"(2) Undefined Error Occured, contact developers\"}"
        )
        val msg = jsonObject.get("detail").toString()
        Timber.tag("FOO").d("(${ex.code()}) ${msg}")
        return "(${ex.code()}) ${msg}"
    }

}