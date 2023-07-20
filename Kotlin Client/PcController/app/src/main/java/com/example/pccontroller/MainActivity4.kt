package com.example.pccontroller

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

lateinit var  text:TextView
var downx:Int=0;
var downy:Int=0;
var upx:Int=0;
var upy:Int=0;
var flag=false;
class MainActivity4 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        text = findViewById(R.id.textview)
        text.setText("mouse")
        var lbutton  = findViewById<Button>(R.id.button13)
        var rubtton = findViewById<Button>(R.id.button14)
        lbutton.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                con.sendcommnad("mouse lclick\n")
            }

        }
        rubtton.setOnClickListener{
            CoroutineScope(Dispatchers.Default).launch{
                con.sendcommnad("mouse rclick\n")
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        text.setText("mouse ${downx - upx} ${downy - upy}");
        val x = event.x.toInt()
        val y = event.y.toInt()

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                downx = x;
                downy = y;
            }
            MotionEvent.ACTION_MOVE->{
              //  downx=x;
              //  downy=y;
                var mdownx=x;
                var mdowny=y;
                GlobalScope.launch {

                    con.sendcommnad("mouse ${(mdownx -downx)/2} ${(mdowny- downy)/2}\n")

                }
            }

//            MotionEvent.ACTION_UP -> {
//                text.setText("mouse up:x=${x}  y = ${y}")
//                upx = x;
//                upy = y;
//                flag = true;
//                GlobalScope.launch {
//
//                    con.sendcommnad("mouse ${downx - upx} ${downy - upy}\n")
//
//                }
           // }

        }

        return false
    }


}




