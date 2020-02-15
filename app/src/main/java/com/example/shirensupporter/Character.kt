package com.example.shirensupporter

import android.util.Log
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.roundToInt

const val MAX_HP = 250
const val MIN_HP = 0
const val MAX_POW = 255
const val MIN_POW = 0
const val MIN_DEF = 0
const val MAX_DEF = 255
const val MIN_DMG = 0
const val MAX_DMG = 255
open class Character(
    val name: String,
    _hp: Int,
    _pow: Int,
    _def: Int) {
    val hp = adjustVal(MIN_HP,MAX_HP,_hp)
    val pow = adjustVal(MIN_POW,MAX_POW,_pow)
    val def = adjustVal(MIN_DEF,MAX_DEF,_def)



    protected fun adjustVal(lowBound: Int,highBound: Int,value: Int): Int =
        max(min(value,highBound),lowBound)

    fun calcDamage(attack: Int,defence: Int): Pair<Int,Int>{
        val baseDmg = (attack*(15.0/16.0).pow(defence))
        Log.d("calc_info","$attack $defence $baseDmg ${adjustVal(0,50,30)}")
        var minDmg = (baseDmg*7.0/8.0).roundToInt()
        var maxDmg = (baseDmg*143.0/128.0).roundToInt()

        minDmg = adjustVal(MIN_DMG,MAX_DMG,minDmg)
        maxDmg = adjustVal(MIN_DMG,MAX_DMG,maxDmg)
        return Pair(minDmg,maxDmg)
    }

}