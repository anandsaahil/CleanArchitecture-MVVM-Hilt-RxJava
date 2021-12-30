package com.data.dataSource.user
import com.data.entity.UserEntity
import io.reactivex.Observable

interface UserDataSource {
    fun getUserDetails() : Observable<List<UserEntity>>
}