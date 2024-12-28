package com.karan.apiintegration

// used to response handling
sealed class Sealedclass<out T> {     //sedaled is the keyword and the Sealed is Class name
    data class Success<out T>(val data: T? = null) : Sealedclass<T>()
    data class Loading(var nothing: Nothing? = null) : Sealedclass<Nothing>()
    data class Error(var msg: String? = null) : Sealedclass<Nothing>()
}