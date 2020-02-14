package com.example.shirensupporter

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MonsterEntity constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val hp: Int,
    val pow: Int,
    val def: Int
)
