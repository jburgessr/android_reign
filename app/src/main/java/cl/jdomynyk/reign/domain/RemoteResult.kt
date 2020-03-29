package cl.jdomynyk.reign.domain

sealed class RemoteResult<out T : Any> {
    data class Success<T : Any>(val items: T) : RemoteResult<T>()
    data class Error(val errorCode: Int, val errorMessage: String) : RemoteResult<Nothing>()
    data class Exception(val exception: kotlin.Exception) : RemoteResult<Nothing>()
}