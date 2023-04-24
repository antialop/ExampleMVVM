package com.example.examplemvvm.data

import com.example.examplemvvm.data.database.dao.QuoteDao
import com.example.examplemvvm.data.database.entities.QuoteEntity
import com.example.examplemvvm.data.model.QuoteModel
import com.example.examplemvvm.data.network.QuoteService
import com.example.examplemvvm.domain.model.Quote
import com.example.examplemvvm.domain.model.toDomain
import javax.inject.Inject

class QuoteRespository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao
) {

    suspend fun getAllQuotesFromApi(): List<Quote> {
        //Llamo al back y se recupera las citas
        val response: List<QuoteModel> = api.getQuotes()
        //QuoteProvider funciona como una pequeña base de datos, se le mete la respuesta del servidor
        return response.map { it.toDomain() }
    }
    suspend fun getAllQuotesFromDataBase(): List<Quote> {
        val response: List<QuoteEntity> = quoteDao.getAllQuotes()
        return response.map { it.toDomain() }
    }
    suspend fun insertQuotes(quotes:List<QuoteEntity>){
        quoteDao.inserAll(quotes)
    }
    suspend fun clearQuotes(){
        quoteDao.deleteAllQuotes()
    }
}


/*
La primera vez que se llame al QuoteRepository -> va a llamar al QuoteService
QuoteService -> hara una llamada al ApiClient a Retrofit que va a devolver ese listado de quotes y se los devuelve al QuoteService
Quote Service ->se los devuelve al QuoteRespository
*/
