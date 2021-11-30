package com.julio.simpleloginscreen.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun registerUser(user : UserEntity) : Long

    @Query("SELECT * FROM user_table WHERE userName like :userName")
    fun getUserInDb(userName : String) : UserEntity


}