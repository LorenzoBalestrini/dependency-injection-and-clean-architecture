package com.example.myapplicationwithauthorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationwithauthorization.network.usecase.MainViewModel
import com.example.myapplicationwithauthorization.network.retrofitcall.RetrofitInstance

class MainViewModelFactory(private val triviaProvider: RetrofitInstance): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(triviaProvider) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}