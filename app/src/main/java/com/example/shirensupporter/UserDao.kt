package com.example.shirensupporter

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM MonsterEntity")
    fun getMonsterAll(): List<MonsterEntity>

    @Insert
    fun insertAllMonster(vararg Monsters: MonsterEntity)

}

