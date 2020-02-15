package com.example.shirensupporter

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.android.synthetic.main.activity_damage_calc.*

const val SAVED_LV = "SAVED_LV"
const val SAVED_POW = "SAVED_POW"
const val SAVED_SPOW ="SAVED_SPOW"
const val SAVED_MON = "SAVED_MON"
const val SAVED_DEF = "SAVED_DEF"
const val SAVED_HP = "SAVED_HP"
class DamageCalc : AppCompatActivity() {

    lateinit var db: UserDataBase
    private val monsterList = mutableMapOf<String,Character>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_damage_calc)

        loadMonsterData()


        initBox()

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
                val player = Player(_hp = playerHp,_swordPow = playerSwordPow,_def = playerDef,
                    _level = playerLevel,_pow = playerPow)
                Toast.makeText(applicationContext,"${monster.def} pow ${player.basePower}",Toast.LENGTH_SHORT).show()

                val attackDmg = player.attackTo(monster)
                minimumDamageTo.text = attackDmg.first.toString()
                maximDamageTo.text = attackDmg.second.toString()

                val attackedDmg = player.attackBy(monster)
                minimumDamageBy.text = attackedDmg.first.toString()
                maximDamageBy.text = attackedDmg.second.toString()

                val prefs = getSharedPreferences(MAIN_PREF, Context.MODE_PRIVATE)
                prefs.edit()
                    .putInt(SAVED_LV,playerLevel)
                    .putInt(SAVED_HP,playerHp)
                    .putInt(SAVED_POW,playerPow)
                    .putInt(SAVED_SPOW,playerSwordPow)
                    .putInt(SAVED_DEF,playerDef)
                    .putString(SAVED_MON,monsterName)
                    .apply()
            }
        }
    }
    private fun initBox(){
        val prefs = getSharedPreferences(MAIN_PREF, Context.MODE_PRIVATE)
        val nowLevel = prefs.getInt(SAVED_LV,1).toString()
        val nowPow = prefs.getInt(SAVED_POW,8).toString()
        val nowSwordPow = prefs.getInt(SAVED_SPOW,0).toString()
        val nowDef = prefs.getInt(SAVED_DEF,0).toString()
        val nowHp = prefs.getInt(SAVED_HP,15).toString()
        val nowMonster = prefs.getString(SAVED_MON,"マムル")

        levelInput.setText(nowLevel,TextView.BufferType.NORMAL)
        powInput.setText(nowPow,TextView.BufferType.NORMAL)
        swordPowInput.setText(nowSwordPow,TextView.BufferType.NORMAL)
        defInput.setText(nowDef,TextView.BufferType.NORMAL)
        hpInput.setText(nowHp,TextView.BufferType.NORMAL)
        monsterInput.setText(nowMonster,TextView.BufferType.NORMAL)
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
