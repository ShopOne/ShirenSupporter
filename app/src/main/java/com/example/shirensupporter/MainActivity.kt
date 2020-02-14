package com.example.shirensupporter

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*

const val MAIN_PREF = "MAIN_KEY"
const val FIRST_START_UP = "FIRST_START_UP"
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
            loadData()
            Log.d("first","yes it is first start up")
            prefs.edit().putBoolean(FIRST_START_UP,false)
                .apply()
        }
    }
    private fun loadData(){
    }
}
