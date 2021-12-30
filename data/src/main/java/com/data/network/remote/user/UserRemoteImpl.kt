package com.data.network.remote.user


import com.data.entity.UserEntity
import com.data.network.service.UserService
import io.reactivex.Observable
import javax.inject.Inject

class UserRemoteImpl @Inject constructor(
    val userService: UserService
) : UserRemote {

    override fun getUserDetails(): Observable<List<UserEntity>> {
        return userService.getUserDetails().map {
            if (it.isSuccessful) {
                return@map it.body()
            } else {
                throw Exception("Something went wrong: error ${it.code()}")
            }
        }.map {
            return@map it.userEntity
        }
    }
}

