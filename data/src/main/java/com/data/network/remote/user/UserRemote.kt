package com.data.network.remote.user

import com.data.entity.*
import io.reactivex.Observable

interface UserRemote {
    fun getUserDetails() : Observable<List<UserEntity>>
}