package flows.common.RequestResult

sealed class RequestResult<out T> {
    data object Loading : RequestResult<Nothing>()
    data class Success<out T>(val data: T) : RequestResult<T>()
    data class Error(val exception: Exception) : RequestResult<Nothing>()
}