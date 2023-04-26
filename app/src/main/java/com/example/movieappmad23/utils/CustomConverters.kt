package com.example.movieappmad23.utils

import androidx.room.TypeConverter
import com.example.movieappmad23.models.Genre
import com.google.gson.Gson

class CustomConverters {

    @TypeConverter
    fun genreListToJson(value: List<Genre>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToGenreList(value: String) = Gson().fromJson(value, Array<Genre>::class.java).toList()

    @TypeConverter
    fun stringListToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToStringList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun stringToGenre(value: String) : Genre {
        return Genre.valueOf(value)
    }

    @TypeConverter
    fun genreToString(value: Genre) : String {
        return value.name
    }
}