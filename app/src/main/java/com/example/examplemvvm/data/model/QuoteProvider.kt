package com.example.examplemvvm.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteProvider @Inject constructor(){
//Al estar inyectado no se necesita estar in companion object, ya se que accede directamente al propio objeto

        var quotes:List<QuoteModel> = emptyList()
}

