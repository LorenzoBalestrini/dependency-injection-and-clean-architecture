package com.example.myapplicationwithauthorization.network.usecase

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplicationwithauthorization.network.retrofitcall.RetrofitInstance
import com.example.myapplicationwithauthorization.network.usecase.model.TriviaQuestion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val KEY_FIRST_RETROFIT_CALL = "first_retrofit_call"

class MainViewModel(private val triviaProvider: RetrofitInstance, private val preferences: SharedPreferences) : ViewModel() {

    sealed class TriviaViewModelEvent {
        data class TriviaResult(val question: List<TriviaQuestion>) : TriviaViewModelEvent()
        data class TriviaError(val message: String) : TriviaViewModelEvent()
        object LastQuestion : TriviaViewModelEvent()
    }

    private var _result = MutableLiveData<TriviaViewModelEvent>()
    val result: LiveData<TriviaViewModelEvent>
        get() = _result

    init {
        getPreferences(preferences)
    }

    private fun getPreferences(preferences: SharedPreferences){
        val triviaPreferences = preferences.getBoolean(KEY_FIRST_RETROFIT_CALL, true)

        if(triviaPreferences){
            preferences.edit().putBoolean(KEY_FIRST_RETROFIT_CALL, false).apply()
            _result.value = TriviaViewModelEvent.LastQuestion
        }
    }

    fun retrieveDetails() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _result.value = TriviaViewModelEvent.TriviaResult(triviaProvider.getDataQuestion())
            } catch (e: Exception) {
                _result.value = TriviaViewModelEvent.TriviaError("Error: ${e.localizedMessage}")
            }
        }
    }
}