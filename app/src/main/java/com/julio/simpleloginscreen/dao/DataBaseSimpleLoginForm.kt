package com.julio.simpleloginscreen.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(version = 1, entities = [UserEntity::class], exportSchema = true)
abstract class DataBaseSimpleLoginForm : RoomDatabase() {

    abstract fun dao() : Dao

    companion object{
        fun getDatabaseInstance(context: Context) : DataBaseSimpleLoginForm{
            return Room.databaseBuilder(context, DataBaseSimpleLoginForm::class.java, "SimpleLoginFormDatabase")
                .build()
        }

    }
}