package com.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.data.cache.db.Constants
import com.data.entity.UserEntity

@TypeConverters(value = [UserDetailTypeConverter::class])
@Entity(tableName = Constants.USER_TABLE)
data class RMUser(
    @PrimaryKey
    var userDetail: List<UserEntity>
)