package com.example.myapplicationwithauthorization

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.myapplicationwithauthorization.network.retrofitcall.RetrofitInstance

class MyApplication: Application() {

    private val triviaProvider = RetrofitInstance()
    lateinit var mainViewModelFactory: MainViewModelFactory
    lateinit var preferences : SharedPreferences

    override fun onCreate() {
        super.onCreate()

        preferences = getSharedPreferences("app", Context.MODE_PRIVATE)
        mainViewModelFactory = MainViewModelFactory(triviaProvider, preferences)
    }
}