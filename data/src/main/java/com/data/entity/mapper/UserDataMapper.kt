package com.data.entity.mapper
import com.data.entity.UserEntity
import com.data.room.RMUser
import com.domain.model.UserDomain
import javax.inject.Inject

class UserDataMapper @Inject constructor() {
    fun mapEntityToDomain(userEntity: UserEntity): UserDomain {
        return UserDomain(
            id = userEntity.id,
            name = userEntity.name,
            email = userEntity.email,
            gender = userEntity.gender,
            status = userEntity.status
        )
    }

    fun mapEntityListToDomainList(userEntityList: List<UserEntity>): List<UserDomain> {
        return userEntityList.map { entityList ->
            mapEntityToDomain(entityList)
        }
    }

    fun mapEntityListToCacheList(userDetailEntity: List<UserEntity>): RMUser {
        return mapEntityToCache(userDetailEntity)
    }

    private fun mapEntityToCache(userDetailEntity: List<UserEntity>): RMUser {
        return RMUser(userDetail = userDetailEntity)
    }
}