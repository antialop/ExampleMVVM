package com.example.examplemvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.examplemvvm.data.database.dao.QuoteDao
import com.example.examplemvvm.data.database.entities.QuoteEntity

//abstract ya que no vamos a implementar ningun metodo
@Database(entities = [QuoteEntity::class], version = 1 )
abstract class QuoteDatabase:RoomDatabase() {

    abstract fun getQuoteDao():QuoteDao
}