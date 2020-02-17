package com.example.shirensupporter

import kotlin.math.min
import kotlin.math.pow
import kotlin.math.roundToInt

const val MIN_LV = 1
const val MAX_LV = 70
const val MIN_TI = 1
const val MAX_TI = 50
const val MIN_SWORD = 0
const val MAX_SWORD = 255
const val MAX_SW_AND_POW = 128.0
class Player(
    _hp: Int,
    _def: Int,
    _level: Int,
    _pow: Int,
    _swordPow: Int
): Character(_hp = _hp,_pow = 0,name = "Shiren",_def = _def){
    val level = adjustVal(MIN_LV,MAX_LV,_level)
    val tikara = adjustVal(MIN_TI,MAX_TI,_pow)
    val swordPow = adjustVal(MIN_SWORD, MAX_SWORD,_swordPow)
    val powByLevel = listOf(
        5,7,9,11,13,15,17,19,21,23,
        26,28,31,34,37,41,44,47,50,53,
        56,58,60,63,66,70,74,78,80,82,
        84,86,88,90,91,92,93,94,95,96,
        97,98,99,100,101,102,103,104,105,106,
        107,108,109,110,111,112,113,115,116,117,
        118,119,120,121,122,123,124,125,126,127
    )
    val basePower = adjustVal(MIN_POW,MAX_POW,
        ((powByLevel[level-1]+powByLevel[level-1]*(min(swordPow+tikara-8.0, MAX_SW_AND_POW))/16.0)).roundToInt())

    fun attackTo(chara: Character): Pair<Int,Int> =
        calcDamage(attack = basePower,defence = chara.def)
    fun attackBy(chara: Character): Pair<Int,Int> =
        calcDamage(attack = chara.pow,defence = def)
}