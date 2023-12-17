package com.example.test_github.Utils

import androidx.room.TypeConverter
import com.example.test_github.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String): Source {
        val typeToken = object : TypeToken<Source>() {}.type
        return Gson().fromJson(value, typeToken)
    }
    @TypeConverter
    fun fromSource(value: Source): String {
        return Gson().toJson(value)
    }
}