package com.james.hayward.artinstituteofchicago.data.common

import retrofit2.HttpException
import retrofit2.Response

interface ApiHandler {
    suspend fun <T : Any> handleApi(
        execute: suspend () -> Response<T>,
    ): ApiResponse<T> {
        return try {
            val response = execute()

            if (response.isSuccessful) {
                ApiResponse.Success(
                    response.code(),
                    response.body()!!,
                )
            } else {
                ApiResponse.Error(response.code(), response.errorBody()?.string())
            }
        } catch (e: HttpException) {
            ApiResponse.Error(e.code(), e.message)
        } catch (e: Throwable) {
            ApiResponse.Exception(e)
        }
    }
}