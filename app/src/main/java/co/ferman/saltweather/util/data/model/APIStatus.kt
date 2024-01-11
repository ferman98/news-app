package co.ferman.saltweather.util.data.model

sealed class APIStatus {
    data object Loading : APIStatus()
    class Success<T>(val data: T) : APIStatus()
    class Error(val errorCode: Int) : APIStatus()
}