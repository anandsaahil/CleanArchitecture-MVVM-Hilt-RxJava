package com.data.repository

import com.data.dataSource.user.UserDataSourceFactoryInterface
import com.data.entity.mapper.UserDataMapper
import com.domain.model.UserDomain
import com.domain.repository.UserRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepository @Inject constructor(
    private val userDataSourceFactory: UserDataSourceFactoryInterface,
    private val userDataMapper: UserDataMapper,
) : UserRepository {

    override fun getUserDetails(): Observable<List<UserDomain>> {
        return userDataSourceFactory.retrieveUserDetailsDataSource().getUserDetails()
            .map {
                return@map userDataMapper.mapEntityListToDomainList(it)
            }
    }
}