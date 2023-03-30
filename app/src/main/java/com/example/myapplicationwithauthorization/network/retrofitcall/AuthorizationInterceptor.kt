package com.example.myapplicationwithauthorization.network.retrofitcall

import okhttp3.Interceptor
import okhttp3.Response

const val API_AUTHORIZATION_HEADER = "X-RapidAPI-Key"

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest = request.newBuilder()
            .addHeader(
                API_AUTHORIZATION_HEADER,
                "bba9fdc651mshc58bdd6e002bf30p18e392jsnd6652b8ccbd4"
            )
            .build()

        return chain.proceed(newRequest)
    }
}