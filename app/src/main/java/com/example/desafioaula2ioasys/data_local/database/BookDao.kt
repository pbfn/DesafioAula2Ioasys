package com.example.desafioaula2ioasys.data_local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.desafioaula2ioasys.data_local.model.BookDataLocal

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBooks(bookList: List<BookDataLocal>)

    @Query("SELECT * FROM Books")
    fun getBooks(): List<BookDataLocal>
}