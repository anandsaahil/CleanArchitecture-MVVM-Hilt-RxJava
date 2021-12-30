package com.data.cache.user

import com.data.entity.UserEntity
import io.reactivex.Observable

interface UserCache {
    fun isUserDetailsExpired(): Boolean
    fun clearRoomData()
    fun getUserDetails(): Observable<List<UserEntity>>
    fun saveUserDetails(userDetailEntity: List<UserEntity>)
    fun setUserDetailsLastCacheTime(lastCache: Long)
}