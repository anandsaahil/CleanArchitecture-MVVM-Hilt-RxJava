package com.data.cache.preference

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
open class PreferenceManager @Inject constructor(@ApplicationContext val context: Context) {

    companion object {
        private const val PREFERENCE_NAME = "com.assignment.preference"
        private const val USER_DETAILS_LAST_FETCH_TIME = "user_details_last_fetch_time"
    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    var userDetailsLastFetchTime: Long
        get() = preferences.getLong(USER_DETAILS_LAST_FETCH_TIME, 0L)
        set(value) = preferences.edit().putLong(USER_DETAILS_LAST_FETCH_TIME, value).apply()

    fun clearAll() {
        this.preferences.edit().clear().apply()
    }
}