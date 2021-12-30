package com.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.data.cache.db.Constants
import com.data.room.RMUser
import io.reactivex.Observable

@Dao
interface UserDao {
    @Query(Constants.QUERY_USER_DETAIL)
    fun getUserDetails(): Observable<RMUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserDetails(cachedUserDetails: RMUser)
}