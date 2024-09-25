package com.pks.shoppingappadmin.practise

interface AuthRepositoryInterface {

    suspend fun register(password:String): Result<Userclass, DataError.NetworkError>
}


data class Userclass(
    val fullName:String,
    val token:String,
    val email:String
)

sealed interface DataError: Error {
    enum class NetworkError: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUEST,
        PAYLOAD_TOO_LARGE
    }
    enum class LocalDataError: DataError {
        DISK_FULL,
        UNKNOWN_ERROR
    }
}