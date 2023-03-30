package com.example.myapplicationwithauthorization

import android.app.Application
import com.example.myapplicationwithauthorization.network.retrofitcall.RetrofitInstance

class MyApplication: Application() {

    private val triviaProvider = RetrofitInstance()
    val mainViewModelFactory = MainViewModelFactory(triviaProvider)

    override fun onCreate() {
        super.onCreate()
    }
}