package com.data.dataSource.user
import com.data.cache.user.UserCache
import com.data.entity.UserEntity
import io.reactivex.Observable
import javax.inject.Inject

class UserCacheDataSource @Inject constructor(private val userCache: UserCache): UserDataSource {
    override fun getUserDetails(): Observable<List<UserEntity>> {
        return userCache.getUserDetails()
    }
}