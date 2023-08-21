package com.mahmoud.android.cinematica.data.repository

import retrofit2.Response

abstract class BaseMovieRepository {
    protected suspend fun <I, O>wrap(
        function: suspend () -> Response<I>,
        mapper: (I) -> O
    ): O {
        val response = function()
        return if(response.isSuccessful){
            response.body()?.let { mapper(it) } ?: throw Throwable()
        }else{
            throw Throwable("response is not successful")
        }
    }
}