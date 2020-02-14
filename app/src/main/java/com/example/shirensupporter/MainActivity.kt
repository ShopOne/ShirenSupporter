package com.example.shirensupporter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newPageBtn.setOnClickListener{
            val intent = Intent(this@MainActivity,DamageCalc::class.java)
            startActivity(intent)
        }
    }
}
