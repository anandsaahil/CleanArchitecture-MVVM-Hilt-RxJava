package com.data.dataSource.user

interface UserDataSourceFactoryInterface {
    fun retrieveUserDetailsDataSource(): UserDataSource
    fun retrieveRemoteDataSource(): UserRemoteDataSource
    fun retrieveCacheDataSource(): UserCacheDataSource
}