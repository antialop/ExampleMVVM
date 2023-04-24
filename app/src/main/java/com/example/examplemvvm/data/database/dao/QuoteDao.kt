package com.example.examplemvvm.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.examplemvvm.data.database.entities.QuoteEntity

@Dao
interface QuoteDao {
    @Query("SELECT * FROM quote_table ORDER BY author DESC")
    suspend fun getAllQuotes():List<QuoteEntity>

    //insertar misma id replace
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserAll(quotes:List<QuoteEntity>)
}