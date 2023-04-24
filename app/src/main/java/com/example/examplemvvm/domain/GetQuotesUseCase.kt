package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRespository
import com.example.examplemvvm.data.database.entities.toDatabase
import com.example.examplemvvm.domain.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(private val repository: QuoteRespository) {

    suspend operator fun invoke(): List<Quote> {
        val quotes = repository.getAllQuotesFromApi()
        return if (quotes.isNotEmpty()) {
            repository.clearQuotes()
            repository.insertQuotes(quotes.map { it.toDatabase() })
            quotes
        } else {
            repository.getAllQuotesFromDataBase()
        }

    }

}
//El objeto en el que se encuentra esta función sera llamado como si fuera una función.
//getQuoteUseCase()