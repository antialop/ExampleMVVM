package com.example.examplemvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examplemvvm.data.model.QuoteModel
import com.example.examplemvvm.domain.GetQuotesUseCase
import com.example.examplemvvm.domain.GetRandomQuoteUseCase
import kotlinx.coroutines.launch

class QuoteViewModel :ViewModel(){
    //quien pone las citas en la interfaz del usuario
    val quoteModel = MutableLiveData<QuoteModel>()
    val isLoading = MutableLiveData<Boolean>()

    var getQuotesUseCase = GetQuotesUseCase()
    var getRandomQuotesUseCase = GetRandomQuoteUseCase()

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
        isLoading.postValue(true)
        val quote: QuoteModel?= getRandomQuotesUseCase()
        if(quote!=null){
            quoteModel.postValue(quote)
        }
        isLoading.postValue(false)
    }


}


