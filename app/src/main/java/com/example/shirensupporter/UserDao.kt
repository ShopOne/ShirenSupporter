package com.example.shirensupporter

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM MonsterEntity")
    fun getMonsterAll(): List<MonsterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMonster(vararg monsters: MonsterEntity)

}

