package com.example.myapplicationwithauthorization.network.retrofitcall

import com.example.myapplicationwithauthorization.network.TriviaApiService
import com.example.myapplicationwithauthorization.network.dto.toTriviaQuestion
import com.example.myapplicationwithauthorization.network.usecase.model.TriviaQuestion
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance{

    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private val authorizationInterceptor = AuthorizationInterceptor()
    private val client = OkHttpClient.Builder()
        .addInterceptor(provideHttpLoggingInterceptor())
        .addInterceptor(authorizationInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://trivia-by-api-ninjas.p.rapidapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private val apiService = retrofit.create(TriviaApiService::class.java)

    suspend fun getDataQuestion(): List<TriviaQuestion> = apiService.getQuestion().map { it.toTriviaQuestion() }
}