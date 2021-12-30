package com.data.cache.user


import android.util.Log
import com.data.cache.dao.UserDao
import com.data.cache.db.UserDatabase
import com.data.cache.preference.PreferenceManager
import com.data.entity.UserEntity
import com.data.entity.mapper.UserDataMapper
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserCacheImpl @Inject constructor(private val userDao: UserDao,
                                        private val userDataMapper: UserDataMapper,
                                        private val database: UserDatabase,
                                        private val userPref: PreferenceManager
) : UserCache {

    //Expiry in minutes
    private val DATA_EXPIRY: Double = 5.0

    override fun clearRoomData() {
        database.clearAllTables()
        userPref.clearAll()
    }

    override fun getUserDetails(): Observable<List<UserEntity>> {
        return userDao.getUserDetails().map {
            return@map it.userDetail
        }
    }

    override fun saveUserDetails(userEntity: List<UserEntity>) {
        userDao.saveUserDetails(userDataMapper.mapEntityListToCacheList(userEntity))
        setUserDetailsLastCacheTime(Date().time / 1000)
    }

    override fun isUserDetailsExpired(): Boolean {
        val lastFetchTime = userPref.userDetailsLastFetchTime
        if (lastFetchTime == 0L) {
            return true
        } else {
            val currTimeInLong = Date().time / 1000
            val timeDiff = currTimeInLong - lastFetchTime
            Log.d("TimeDiffCal", timeDiff.toString())
            return (timeDiff > DATA_EXPIRY * 60)
        }
    }

    override fun setUserDetailsLastCacheTime(lastCache: Long) {
        userPref.userDetailsLastFetchTime = lastCache
    }
}