package com.data.entity

import com.google.gson.annotations.SerializedName

data class UserResponseEntity(
    @SerializedName("data")
    val userEntity: List<UserEntity>?
)