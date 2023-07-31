package com.example.news24x7.db

import androidx.room.TypeConverter
import com.example.news24x7.models.Source


class Converters {

    @TypeConverter
    fun fromSource(source: Source): String{
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source{
        return Source(name, name)
    }

}