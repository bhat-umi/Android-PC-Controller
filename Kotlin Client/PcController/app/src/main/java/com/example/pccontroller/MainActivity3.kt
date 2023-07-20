package com.example.pccontroller

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity3 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)








    }



    fun senddata(view: View ) {
        lateinit  var  btn:Button;
        btn= view as Button;
        var str=btn.text;




        GlobalScope.launch {
            con.sendcommnad("key "+str.toString() + "\n")
        }
    }
}