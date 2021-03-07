package pl.mclojek.carpify.network

// A generic class that contains data and status about loading this data.
sealed class ApiResponse<T>(
        val data: T? = null,
        val message: String? = null
) {
    class Success<T>(data: T) : ApiResponse<T>(data)
    class Loading<T>(data: T? = null) : ApiResponse<T>(data)
    class Error<T>(message: String, data: T? = null) : ApiResponse<T>(data, message)
}