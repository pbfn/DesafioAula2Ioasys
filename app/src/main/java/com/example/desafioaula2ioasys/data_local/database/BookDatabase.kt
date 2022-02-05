package com.example.desafioaula2ioasys.data_local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.desafioaula2ioasys.data_local.model.BookDataLocal

@Database(entities = [BookDataLocal::class], version = 1)
@TypeConverters(Converters::class)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

}