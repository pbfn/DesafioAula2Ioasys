package com.example.desafioaula2ioasys.data_local.database

import androidx.room.TypeConverter
import com.google.gson.Gson




class Converters {

    @TypeConverter
    fun listToJson(value: List<String>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<String> {
        val objects = Gson().fromJson(value, Array<String>::class.java) as Array<String>
        val list = objects.toList()
        return list
    }
}