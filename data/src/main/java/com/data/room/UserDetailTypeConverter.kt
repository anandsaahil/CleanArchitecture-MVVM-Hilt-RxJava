package com.data.room

import androidx.room.TypeConverter
import com.data.entity.UserEntity
import com.google.gson.Gson

class UserDetailTypeConverter {
    @TypeConverter
    fun listToJson(value: List<UserEntity>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<UserEntity>::class.java).toList()
}