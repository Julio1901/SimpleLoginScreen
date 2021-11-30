package com.julio.simpleloginscreen.dao

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
class UserEntity (
    @PrimaryKey(autoGenerate = false)
    val userName : String,
    val email : String,
    val password : String
        )