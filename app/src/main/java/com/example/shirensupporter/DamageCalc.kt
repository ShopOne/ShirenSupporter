package com.example.shirensupporter

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.android.synthetic.main.activity_damage_calc.*

class DamageCalc : AppCompatActivity() {

    lateinit var db: UserDataBase
    private val monsterList = mutableMapOf<String,Character>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_damage_calc)

        loadMonsterData()

        runCalcBtn.setOnClickListener {
            val playerLevel = levelInput.text.toString().toIntOrNull()?:1
            val playerHp = hpInput.text.toString().toIntOrNull()?:15
            val playerPow = powInput.text.toString().toIntOrNull()?:8
            val playerSwordPow = swordPowInput.text.toString().toIntOrNull()?:0
            val playerDef = defInput.text.toString().toIntOrNull()?:0
            val monsterName = monsterInput.text.toString()
            val monster = monsterList[monsterName]
            if(monster==null){
                Toast.makeText(applicationContext,"モンスターが見つかりません",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext,"${monster.def} pow $playerHp",Toast.LENGTH_SHORT).show()
                val player = Player(_hp = playerHp,_swordPow = playerSwordPow,_def = playerDef,
                    _level = playerLevel,_pow = playerPow)
                val attackDmg = player.attackTo(monster)
                val attackedDmg = player.attackBy(monster)
                minimumDamageTo.text = attackDmg.first.toString()
                maximDamageTo.text = attackDmg.second.toString()

                minimumDamageBy.text = attackedDmg.first.toString()
                maximDamageBy.text = attackedDmg.second.toString()
            }
        }
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
                monsterList += mapOf(it.name to
                    Character(name = it.name,_hp = it.hp,_pow = it.pow,_def=it.def))
            }
        }
    }
}
