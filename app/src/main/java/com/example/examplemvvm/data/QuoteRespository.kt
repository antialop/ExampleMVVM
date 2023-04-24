package com.example.examplemvvm.data

import com.example.examplemvvm.data.model.QuoteModel
import com.example.examplemvvm.data.model.QuoteProvider
import com.example.examplemvvm.data.network.QuoteService
import javax.inject.Inject

class QuoteRespository @Inject constructor(private val api: QuoteService, private val quoteProvider: QuoteProvider) {

    suspend fun getAllQuotes():List<QuoteModel>{
        //Llamo al back y se recupera las citas
        val response:List<QuoteModel> = api.getQuotes()
        //QuoteProvider funciona como una pequeña base de datos, se le mete la respuesta del servidor
        quoteProvider.quotes = response
        return response
    }
}
/*
La primera vez que se llame al QuoteRepository -> va a llamar al QuoteService
QuoteService -> hara una llamada al ApiClient a Retrofit que va a devolver ese listado de quotes y se los devuelve al QuoteService
Quote Service ->se los devuelve al QuoteRespository
*/
