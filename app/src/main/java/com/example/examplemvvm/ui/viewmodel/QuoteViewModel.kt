package com.example.examplemvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examplemvvm.data.model.QuoteModel
import com.example.examplemvvm.data.model.QuoteProvider
import com.example.examplemvvm.domain.GetQuotesUseCase
import kotlinx.coroutines.launch

class QuoteViewModel :ViewModel(){
    //quien pone las citas en la interfaz del usuario
    val quoteModel = MutableLiveData<QuoteModel>()
    val isLoading = MutableLiveData<Boolean>()

    var getQuotesUseCase = GetQuotesUseCase()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue((true))
            val result: List<QuoteModel>? = getQuotesUseCase()
            if(!result.isNullOrEmpty()){
                //Esto es para iniciar la aplicación
                quoteModel.postValue(result[0])
                isLoading.postValue((false))
            }
        }
    }

     /*
    result llamara a GetQuoteUseCase
    GetQuoteUseCase llama a QuoteRespository
    La primera vez que se llame al QuoteRepository -> va a llamar al QuoteService
    QuoteService -> hara una llamada al ApiClient a Retrofit que va a devolver ese listado de quotes y se los devuelve al QuoteService
    Quote Service ->se los devuelve al QuoteRespository
    */
    fun randomQuote(){
//        val currentQuote: QuoteModel = QuoteProvider.random()
//        quoteModel.postValue((currentQuote))
    }


}