package com.data.dataSource.user

import com.data.cache.user.UserCache
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataSourceFactory @Inject constructor(
    private val userCache: UserCache,
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userCacheDataSource: UserCacheDataSource
) : UserDataSourceFactoryInterface {

    override fun retrieveUserDetailsDataSource(): UserDataSource {
        return if (!userCache.isUserDetailsExpired()) {
            retrieveCacheDataSource()
        } else {
            retrieveRemoteDataSource()
        }
    }

    override fun retrieveRemoteDataSource(): UserRemoteDataSource {
        return userRemoteDataSource
    }

    override fun retrieveCacheDataSource(): UserCacheDataSource {
        return userCacheDataSource
    }

}