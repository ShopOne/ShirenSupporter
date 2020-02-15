package com.example.shirensupporter

import android.app.Application
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException

const val MAIN_PREF = "MAIN_KEY"
const val FIRST_START_UP = "FIRST_START_UP"
const val MONSTER_FILE = "rawMonsterData.txt"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        newPageBtn.setOnClickListener{
            val intent = Intent(this@MainActivity,DamageCalc::class.java)
            startActivity(intent)
        }
        val prefs = getSharedPreferences(MAIN_PREF,AppCompatActivity.MODE_PRIVATE)
        val isFirst = prefs.getBoolean(FIRST_START_UP,true)
        if(isFirst){
            firstProcess(prefs)
        }
    }
    private fun firstProcess(prefs: SharedPreferences){
        inputData()
        Log.d("first","it is first start up")
        prefs.edit().putBoolean(FIRST_START_UP,false)
            .apply()
    }
    private fun inputData(){
        val db = Room.databaseBuilder(
            applicationContext,
            UserDataBase::class.java, "database"
        )
            .enableMultiInstanceInvalidation()
            .build()
        val dao = db.userDao()
        AsyncTask.execute{
            dao.insertAllMonster(
                MonsterEntity(id = 0,name = "アイアンヘッド",hp = 15,pow = 13,def = 5),
                MonsterEntity(id = 1,name = "チェインヘッド",hp = 60,pow = 48,def = 21),
                MonsterEntity(id = 2,name = "ギガヘッド",hp = 65,pow = 255,def = 30),
                MonsterEntity(id = 3,name = "いやしウサギ",hp = 6,pow = 7,def = 28),
                MonsterEntity(id = 4,name = "エーテルデビル",hp = 50,pow = 27,def = 12),
                MonsterEntity(id = 5,name = "おばけ大根",hp = 8,pow = 5,def = 10),
                MonsterEntity(id = 6,name = "めまわし大根",hp = 45,pow = 27,def = 13),
                MonsterEntity(id = 7,name = "ねむり大根",hp = 50,pow = 27,def = 17),
                MonsterEntity(id = 8,name = "がいこつまどう",hp = 23,pow = 14,def = 11),
                MonsterEntity(id = 9,name = "がいこつまじん",hp = 39,pow = 27,def = 19),
                MonsterEntity(id = 10,name = "がいこつまおう",hp = 60,pow = 55,def = 21),
                MonsterEntity(id = 11,name = "火炎入道",hp = 35,pow = 32,def = 12),
                MonsterEntity(id = 12,name = "火炎入道2",hp = 65,pow = 48,def = 19),
                MonsterEntity(id = 13,name = "火炎入道3",hp = 70,pow = 243,def = 27),
                MonsterEntity(id = 14,name = "ガマラ",hp = 10,pow = 0,def = 7),
                MonsterEntity(id = 15,name = "ガマグッチ",hp = 40,pow = 0,def = 5),
                MonsterEntity(id = 16,name = "ガマゴン",hp = 50,pow = 0,def = 34),
                MonsterEntity(id = 17,name = "カラクロイド",hp = 50,pow = 48,def = 21),
                MonsterEntity(id = 18,name = "キグニ族",hp = 40,pow = 17,def = 8),
                MonsterEntity(id = 19,name = "キグニ族2",hp = 60,pow = 39,def = 15),
                MonsterEntity(id = 20,name = "キグニ族3",hp = 70,pow = 48,def = 17),
                MonsterEntity(id = 21,name = "鬼面武者",hp = 10,pow = 7,def = 17),
                MonsterEntity(id = 22,name = "はんにゃ武者",hp = 16,pow = 17,def = 19),
                MonsterEntity(id = 23,name = "将軍",hp = 40,pow = 24,def = 21),
                MonsterEntity(id = 24,name = "ぼうれい武者",hp = 4,pow = 13,def = 17),
                MonsterEntity(id = 25,name = "ギャザー",hp = 50,pow = 38,def = 19),
                MonsterEntity(id = 26,name = "キラーギャザー",hp = 60,pow = 208,def = 33),
                MonsterEntity(id = 27,name = "ヘルギャザー",hp = 120,pow = 255,def = 29),
                MonsterEntity(id = 28,name = "吸引幼虫",hp = 40,pow = 24,def = 15),
                MonsterEntity(id = 29,name = "吸引虫",hp = 40,pow = 25,def = 21),
                MonsterEntity(id = 30,name = "吸引成虫",hp = 80,pow = 255,def = 29),
                MonsterEntity(id = 31,name = "きり仙人",hp = 30,pow = 17,def = 15),
                MonsterEntity(id = 32,name = "みの仙人",hp = 65,pow = 39,def = 17),
                MonsterEntity(id = 33,name = "かすみ仙人",hp = 90,pow = 255,def = 26),
                MonsterEntity(id = 34,name = "くねくねハニー",hp = 30,pow = 24,def = 15),
                MonsterEntity(id = 35,name = "ゲイズ",hp = 20,pow = 17,def = 12),
                MonsterEntity(id = 36,name = "スーパーゲイズ",hp = 51,pow = 29,def = 19),
                MonsterEntity(id = 37,name = "ハイパーゲイズ",hp = 110,pow = 255,def = 33),
                MonsterEntity(id = 38,name = "小僧天狗",hp = 6,pow = 3,def = 4),
                MonsterEntity(id = 39,name = "カラス天狗",hp = 6,pow = 9,def = 13),
                MonsterEntity(id = 40,name = "天狗師匠",hp = 7,pow = 10,def = 10),
                MonsterEntity(id = 41,name = "死の使い",hp = 25,pow = 15,def = 8),
                MonsterEntity(id = 42,name = "地獄の使者",hp = 45,pow = 18,def = 18),
                MonsterEntity(id = 43,name = "死神",hp = 65,pow = 33,def = 25),
                MonsterEntity(id = 44,name = "シューベル",hp = 20,pow = 20,def = 32),
                MonsterEntity(id = 45,name = "メンベルス",hp = 30,pow = 29,def = 26),
                MonsterEntity(id = 46,name = "ベルトーベン",hp = 70,pow = 255,def = 30),
                MonsterEntity(id = 47,name = "正面戦士",hp = 50,pow = 48,def = 35),
                MonsterEntity(id = 48,name = "正面ファイター",hp = 100,pow = 255,def = 35),
                MonsterEntity(id = 49,name = "正面マスター",hp = 250,pow = 255,def = 33),
                MonsterEntity(id = 50,name = "セルアーマー",hp = 20,pow = 21,def = 16),
                MonsterEntity(id = 51,name = "クロムアーマー",hp = 65,pow = 48,def = 20),
                MonsterEntity(id = 52,name = "チタンアーマー",hp = 80,pow = 255,def = 30),
                MonsterEntity(id = 53,name = "タウロス",hp = 12,pow = 9,def = 18),
                MonsterEntity(id = 54,name = "ミノタウロス",hp = 45,pow = 42,def = 18),
                MonsterEntity(id = 55,name = "メガタウロス",hp = 100,pow = 255,def = 28),
                MonsterEntity(id = 56,name = "チキン",hp = 4,pow = 1,def = 1),
                MonsterEntity(id = 57,name = "マスターチキン",hp = 50,pow = 43,def = 22),
                MonsterEntity(id = 58,name = "グレートチキン",hp = 90,pow = 255,def = 26),
                MonsterEntity(id = 59,name = "デブータ",hp = 23,pow = 9,def = 9),
                MonsterEntity(id = 60,name = "デブーチョ",hp = 50,pow = 22,def = 25),
                MonsterEntity(id = 61,name = "デブートン",hp = 105,pow = 255,def = 26),
                MonsterEntity(id = 62,name = "とおせんりゅう",hp = 12,pow = 11,def = 11),
                MonsterEntity(id = 63,name = "ドラゴンヘッド",hp = 75,pow = 55,def = 23),
                MonsterEntity(id = 64,name = "スルードラゴン",hp = 80,pow = 243,def = 27),
                MonsterEntity(id = 65,name = "ドラゴン",hp = 90,pow = 76,def = 28),
                MonsterEntity(id = 66,name = "スカイドラゴン",hp = 100,pow = 255,def = 35),
                MonsterEntity(id = 67,name = "アークドラゴン",hp = 120,pow = 255,def = 39),
                MonsterEntity(id = 68,name = "ドレムラス",hp = 45,pow = 36,def = 17),
                MonsterEntity(id = 69,name = "マッドレムラス",hp = 70,pow = 50,def = 24),
                MonsterEntity(id = 70,name = "ハードレムラス",hp = 130,pow = 255,def = 28),
                MonsterEntity(id = 71,name = "ナイフゲータ",hp = 7,pow = 4,def = 1),
                MonsterEntity(id = 72,name = "サーベルゲータ",hp = 40,pow = 27,def = 16),
                MonsterEntity(id = 73,name = "ブレイドゲータ",hp = 100,pow = 255,def = 30),
                MonsterEntity(id = 74,name = "ぬすっトド",hp = 35,pow = 0,def = 6),
                MonsterEntity(id = 75,name = "みどりトド",hp = 51,pow = 0,def = 12),
                MonsterEntity(id = 76,name = "アイアントド",hp = 70,pow = 0,def = 22),
                MonsterEntity(id = 77,name = "ノロージョ",hp = 27,pow = 27,def = 10),
                MonsterEntity(id = 78,name = "ノロージョの姉",hp = 40,pow = 36,def = 19),
                MonsterEntity(id = 79,name = "ノロージョの母",hp = 75,pow = 104,def = 31),
                MonsterEntity(id = 80,name = "パコレプキン",hp = 27,pow = 26,def = 10),
                MonsterEntity(id = 81,name = "パコレプキーナ",hp = 60,pow = 39,def = 15),
                MonsterEntity(id = 82,name = "パコレプキング",hp = 60,pow = 173,def = 27),
                MonsterEntity(id = 83,name = "畠荒らし",hp = 10,pow = 8,def = 11),
                MonsterEntity(id = 84,name = "壺荒らし",hp = 15,pow = 13,def = 11),
                MonsterEntity(id = 85,name = "物荒らし",hp = 20,pow = 19,def = 11),
                MonsterEntity(id = 86,name = "ハブーン",hp = 11,pow = 10,def = 9),
                MonsterEntity(id = 87,name = "マムーン",hp = 25,pow = 26,def = 18),
                MonsterEntity(id = 88,name = "ニシキーン",hp = 70,pow = 229,def = 30),
                MonsterEntity(id = 89,name = "ぴーたん",hp = 5,pow = 2,def = 14),
                MonsterEntity(id = 90,name = "逃げピータン",hp = 5,pow = 2,def = 37),
                MonsterEntity(id = 91,name = "飛びぴーたん",hp = 10,pow = 2,def = 31),
                MonsterEntity(id = 92,name = "兵隊アリ",hp = 18,pow = 23,def = 12),
                MonsterEntity(id = 93,name = "隊長アリ",hp = 45,pow = 39,def = 18),
                MonsterEntity(id = 94,name = "大将アリ",hp = 60,pow = 66,def = 24),
                MonsterEntity(id = 95,name = "ボウヤー",hp = 7,pow = 4,def = 10),
                MonsterEntity(id = 96,name = "クロスボウヤー",hp = 20,pow = 14,def = 14),
                MonsterEntity(id = 97,name = "コドモ戦車",hp = 25,pow = 17,def = 9),
                MonsterEntity(id = 98,name = "ちびタンク",hp = 25,pow = 20,def = 23),
                MonsterEntity(id = 99,name = "オヤジ戦車",hp = 30,pow = 9,def = 18),
                MonsterEntity(id = 100,name = "ガンコ戦車",hp = 40,pow = 10,def = 26),
                MonsterEntity(id = 101,name = "イッテツ戦車",hp = 50,pow = 238,def = 39),
                MonsterEntity(id = 102,name = "マムル",hp = 5,pow = 2,def = 1),
                MonsterEntity(id = 103,name = "あなぐらマムル",hp = 5,pow = 3,def = 9),
                MonsterEntity(id = 104,name = "洞窟マムル",hp = 5,pow = 255,def = 78),
                MonsterEntity(id = 105,name = "豆山賊",hp = 6,pow = 4,def = 1),
                MonsterEntity(id = 106,name = "山賊",hp = 12,pow = 7,def = 10),
                MonsterEntity(id = 107,name = "山賊親分",hp = 12,pow = 9,def = 13),
                MonsterEntity(id = 108,name = "まわるポリゴン",hp = 20,pow = 13,def = 8),
                MonsterEntity(id = 109,name = "おどるポリゴン",hp = 80,pow = 29,def = 15),
                MonsterEntity(id = 110,name = "うたうポリゴン",hp = 80,pow = 208,def = 25),
                MonsterEntity(id = 111,name = "ミドロ",hp = 12,pow = 0,def = 9),
                MonsterEntity(id = 112,name = "ゲドロ",hp = 35,pow = 0,def = 15),
                MonsterEntity(id = 113,name = "オドロ",hp = 60,pow = 0,def = 30),
                MonsterEntity(id = 114,name = "やみふくろう",hp = 4,pow = 9,def = 32),
                MonsterEntity(id = 115,name = "妖怪にぎり変化",hp = 10,pow = 7,def = 4),
                MonsterEntity(id = 116,name = "妖怪にぎり親方",hp = 15,pow = 13,def = 11),
                MonsterEntity(id = 117,name = "妖怪にぎり元シメ",hp = 50,pow = 31,def = 16),
                MonsterEntity(id = 118,name = "ンドゥバ",hp = 50,pow = 15,def = 1),
                MonsterEntity(id = 119,name = "ンドゥルー",hp = 50,pow = 39,def = 20),
                MonsterEntity(id = 120,name = "ンバマ",hp = 50,pow = 44,def = 29),
                MonsterEntity(id = 121,name = "魔触虫",hp = 250,pow = 150,def = 26),
                MonsterEntity(id = 122,name = "店主",hp = 250,pow = 217,def = 24),
                MonsterEntity(id = 123,name = "盗賊番",hp = 250,pow = 217,def = 24),
                MonsterEntity(id = 124,name = "番犬",hp = 78,pow = 44,def = 27)
            )
        }
    }
}
