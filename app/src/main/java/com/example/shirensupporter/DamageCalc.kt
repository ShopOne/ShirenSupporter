package com.example.shirensupporter

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class DamageCalc : AppCompatActivity() {

    lateinit var db: UserDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_damage_calc)

        loadMonsterData()
    }
    private fun loadMonsterData(){
        val db = Room.databaseBuilder(
            applicationContext,
            UserDataBase::class.java, "database")
            .enableMultiInstanceInvalidation()
            .build()
        val dao = db.userDao()
        AsyncTask.execute{
            val data = dao.getMonsterAll()
            data.forEach{
                Log.d("monster","${it.name} ${it.hp}")
            }
        }
    }
}
