package com.example.examplemvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.examplemvvm.model.QuoteModel
import com.example.examplemvvm.model.QuoteProvider

class QuoteViewModel :ViewModel(){
    val quoteModel = MutableLiveData<QuoteModel>()
    //Esta funcion será llamada por la activity
    fun randomQuote(){
        val currentQuote: QuoteModel = QuoteProvider.random()
        quoteModel.postValue((currentQuote))
    }
}