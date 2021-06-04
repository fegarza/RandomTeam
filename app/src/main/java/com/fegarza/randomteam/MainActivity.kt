package com.fegarza.randomteam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun goHome(vista: View){
        var miIntencion = Intent(this, HomeActivity::class.java);
        startActivity(miIntencion)
    }
}