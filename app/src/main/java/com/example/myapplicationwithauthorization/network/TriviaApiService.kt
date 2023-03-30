package com.example.myapplicationwithauthorization.network

import com.example.myapplicationwithauthorization.network.dto.QuestionData
import retrofit2.http.GET

interface TriviaApiService {
    @GET("/v1/trivia")
    suspend fun getQuestion() : QuestionData
}