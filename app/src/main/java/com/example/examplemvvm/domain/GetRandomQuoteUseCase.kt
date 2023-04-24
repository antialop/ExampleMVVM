package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRespository
import com.example.examplemvvm.data.model.QuoteModel
import com.example.examplemvvm.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(private val quoteProvider: QuoteRespository){

    suspend operator fun invoke(): Quote?{
        val quotes = quoteProvider.getAllQuotesFromDataBase()
        if(!quotes.isNullOrEmpty()){
            val randomNumber = (quotes.indices).random()
            return quotes[randomNumber]
        }
        return null
    }
}