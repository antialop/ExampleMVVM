package com.example.examplemvvm.data

import com.example.examplemvvm.data.model.QuoteModel
import com.example.examplemvvm.data.network.QuoteService

class QuoteRespository {
    private val api = QuoteService()
    suspend fun getAllQuotes():List<QuoteModel>{
        val response:List<QuoteModel> = api.getQuotes()
        return response
    }
}