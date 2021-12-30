package com.data.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.data.cache.dao.UserDao
import com.data.cache.db.Constants.DATABASE_NAME
import com.data.room.RMUser

object Constants {
    const val USER_TABLE = "USER_TABLE"
    const val QUERY_USER_DETAIL = "SELECT * FROM $USER_TABLE"
    const val DATABASE_NAME = "user_database.db"
}

@Database(entities = [RMUser::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    companion object {
        @Volatile
        private var instance: UserDatabase? = null
        fun getInstance(context: Context): UserDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
        private fun buildDatabase(context: Context): UserDatabase {
            return Room.databaseBuilder(context.applicationContext, UserDatabase::class.java, DATABASE_NAME).build()
        }
    }
}