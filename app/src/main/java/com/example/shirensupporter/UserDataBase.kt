package com.example.shirensupporter

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(MonsterEntity::class),version = 1)
abstract class UserDataBase: RoomDatabase(){
    abstract fun userDao(): UserDao
}