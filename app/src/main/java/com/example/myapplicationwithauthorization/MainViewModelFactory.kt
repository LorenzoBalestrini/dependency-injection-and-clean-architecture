package com.example.myapplicationwithauthorization

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationwithauthorization.network.usecase.MainViewModel
import com.example.myapplicationwithauthorization.network.retrofitcall.RetrofitInstance

class MainViewModelFactory(private val triviaProvider: RetrofitInstance, private val preferences: SharedPreferences): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(triviaProvider, preferences) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}