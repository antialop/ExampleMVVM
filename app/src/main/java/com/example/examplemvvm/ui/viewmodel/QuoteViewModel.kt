package com.example.examplemvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examplemvvm.data.model.QuoteModel
import com.example.examplemvvm.domain.GetQuotesUseCase
import com.example.examplemvvm.domain.GetRandomQuoteUseCase
import com.example.examplemvvm.domain.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private var getQuotesUseCase:GetQuotesUseCase,
    private var getRandomQuotesUseCase: GetRandomQuoteUseCase
):ViewModel(){
    //quien pone las citas en la interfaz del usuario
    val quoteModel = MutableLiveData<Quote>()
    val isLoading = MutableLiveData<Boolean>()



    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result: List<Quote> =getQuotesUseCase()
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
        viewModelScope.launch {
            isLoading.postValue(true)
            val quote = getRandomQuotesUseCase()
            if(quote!=null){
                quoteModel.postValue(quote)
            }
            isLoading.postValue(false)
        }

    }


}


