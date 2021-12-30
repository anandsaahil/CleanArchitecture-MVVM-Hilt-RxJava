package com.data.dataSource.user

import com.data.cache.user.UserCache
import com.data.entity.UserEntity
import com.data.network.remote.user.UserRemote
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteDataSource @Inject constructor(
    private val userRemote: UserRemote,
    private val userCache: UserCache
) :
    UserDataSource {
    override fun getUserDetails(): Observable<List<UserEntity>> {
        return userRemote.getUserDetails().doOnNext {
            userCache.saveUserDetails(it)
        }
    }
}