package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRespository
import com.example.examplemvvm.data.model.QuoteModel

class GetQuotesUseCase {
    private val repository = QuoteRespository()
    suspend operator fun invoke()= repository.getAllQuotes()

}
//El objeto en el que se encuentra esta función sera llamado como si fuera una función.
//getQuoteUseCase()