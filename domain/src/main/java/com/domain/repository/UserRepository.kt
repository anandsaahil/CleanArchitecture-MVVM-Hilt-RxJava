package com.domain.repository

import com.domain.model.UserDomain
import io.reactivex.Observable

interface UserRepository {
    fun getUserDetails() : Observable<List<UserDomain>>
}