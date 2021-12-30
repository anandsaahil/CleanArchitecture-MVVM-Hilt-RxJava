package com.data.network.service

import com.data.entity.UserResponseEntity
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET("users/")
    fun getUserDetails() : Observable<Response<UserResponseEntity>>
}