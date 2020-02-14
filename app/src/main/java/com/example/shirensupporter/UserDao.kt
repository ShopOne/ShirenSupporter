package com.example.shirensupporter

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM MonsterEntity")
    fun getMonsterAll(): List<MonsterEntity>
}