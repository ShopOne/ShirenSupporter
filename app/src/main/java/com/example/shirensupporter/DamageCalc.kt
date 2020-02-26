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
const val INF = 1000000000
class DamageCalc : AppCompatActivity() {

    lateinit var db: UserDataBase
    private val monsterList = mutableMapOf<String,Character>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_damage_calc)

        loadMonsterData()


        initBox()

        runCalcBtn.setOnClickListener {
            val monsterName = monsterInput.text.toString()
            val monster = monsterList[monsterName]
            if(monster==null){
                Toast.makeText(applicationContext,"モンスターが見つかりません",Toast.LENGTH_SHORT).show()
            }else{
                val player = Player(
                    _hp = hpInput.text.toString().toIntOrNull()?:15,
                    _swordPow = swordPowInput.text.toString().toIntOrNull()?:0,
                    _def = defInput.text.toString().toIntOrNull()?:0,
                    _level = levelInput.text.toString().toIntOrNull()?:1,
                    _tikara = powInput.text.toString().toIntOrNull()?:8)

                val attackDmg = player.attackTo(monster)
                val attackedDmg = player.attackBy(monster)
                // 切り上げの式 firstは最大値 secondは最小値
                val beatCount = if(attackDmg.second != 0) (monster.hp + attackDmg.second - 1) / attackDmg.second
                                else INF
                val beatenCount = if(attackedDmg.first != 0)(player.hp + attackedDmg.first - 1) / attackedDmg.first
                                  else INF

                val prefs = getSharedPreferences(MAIN_PREF, Context.MODE_PRIVATE)
                prefs.edit()
                    .putInt(SAVED_LV,player.level)
                    .putInt(SAVED_HP,player.hp)
                    .putInt(SAVED_POW,player.tikara)
                    .putInt(SAVED_SPOW,player.swordPow)
                    .putInt(SAVED_DEF,player.def)
                    .putString(SAVED_MON,monsterName)
                    .apply()

                setTextToView(
                    player,
                    attackDmg,
                    attackedDmg,
                    beatCount,
                    beatenCount
                )
            }
        }
    }

    private fun setTextToView(
        player: Player,
        attackDmg: Pair<Int, Int>,
        attackedDmg: Pair<Int, Int>,
        beatCount: Int,
        beatenCount: Int
    ) {
        var beatText = ""
        var beatenText = ""
        levelInput.setText(player.level.toString())
        hpInput.setText(player.hp.toString())
        powInput.setText(player.tikara.toString())
        swordPowInput.setText(player.swordPow.toString())
        defInput.setText(player.def.toString())

        minimumDamageTo.text = attackDmg.first.toString()
        maximDamageTo.text = attackDmg.second.toString()

        minimumDamageBy.text = attackedDmg.first.toString()
        maximDamageBy.text = attackedDmg.second.toString()


        beatText += if(beatCount != INF) beatCount.toString() + resources.getString(R.string.beat_text)
                    else resources.getString(R.string.cant_beat_text)

        beatenText += if(beatenCount != INF) beatenCount.toString() + resources.getString(R.string.beaten_text)
                      else resources.getString(R.string.cant_beaten_text)

        beatTextView.text = beatText
        beatenTextView.text = beatenText
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
