package com.example.myapplicationwithauthorization.network.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplicationwithauthorization.network.retrofitcall.RetrofitInstance
import com.example.myapplicationwithauthorization.network.usecase.model.TriviaQuestion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val triviaProvider: RetrofitInstance) : ViewModel() {

    sealed class TriviaResult {
        data class Details(val question: List<TriviaQuestion>) : TriviaResult()
        data class Error(val message: String) : TriviaResult()
    }

    private var _result = MutableLiveData<TriviaResult>()
    val result: LiveData<TriviaResult>
        get() = _result


    fun retrieveDetails() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _result.value = TriviaResult.Details(triviaProvider.getDataQuestion())
            } catch (e: Exception) {
                _result.value = TriviaResult.Error("Error: ${e.localizedMessage}")
            }
        }
    }


}