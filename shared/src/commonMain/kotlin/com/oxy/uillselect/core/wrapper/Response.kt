package com.oxy.uillselect.core.wrapper

import com.oxy.uillselect.core.wrapper.Response.Companion.HTTP_CODE_SUCCESS

data class Response<T>(
    val data: T? = null,
    val message: String = DEFAULT_MESSAGE,
    val code: Int = HTTP_CODE_SUCCESS
) {
    companion object {
        inline fun <reified T> success(data: T? = null): Response<T> = Response(data)
        inline fun <reified T> failure(message: String = DEFAULT_MESSAGE): Response<T> = Response(message = message)
        inline fun <reified R> produce(producer: () -> R): Response<R> = try {
            success(producer())
        } catch (e: Exception) {
            val message = e.message ?: DEFAULT_MESSAGE
            failure(message = message)
        }

        const val HTTP_CODE_SUCCESS = 200
        const val DEFAULT_MESSAGE = "<error>"
    }
}

val Response<*>.isSuccess: Boolean get() = code == HTTP_CODE_SUCCESS
val Response<*>.isFailure: Boolean get() = code != HTTP_CODE_SUCCESS

fun <T> Response<T>.success(): T = data!!
fun Response<*>.message(): String = message
fun Response<*>.exception(): Exception = Exception(message())