package co.ferman.saltweather.util.data.model

sealed class APIStatus<out T> {
    data object Loading : APIStatus<Nothing>()
    class Error(val errorCode: Int) : APIStatus<Nothing>()
    class Success<T>(val data: T) : APIStatus<T>()
}