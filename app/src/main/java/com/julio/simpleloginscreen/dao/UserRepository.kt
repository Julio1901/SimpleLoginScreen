package com.julio.simpleloginscreen.dao



class UserRepository(private val dao : Dao) {

    //Returns -1 if not entered
    fun registerUser(user : UserEntity) : Long {

        return dao.registerUser(user)
    }

    fun getUserInDb(name : String) : UserEntity{
        return dao.getUserInDb(name)
    }
}