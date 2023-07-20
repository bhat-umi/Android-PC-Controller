package com.example.pccontroller

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        Toast.makeText(this,"Connected....",Toast.LENGTH_SHORT).show()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var keybutton = findViewById<Button>(R.id.keyboard)
        var mousebutton = findViewById<Button>(R.id.mouse)
        var sharebutton = findViewById<Button>(R.id.share)

        keybutton.setOnClickListener{

            var intent:Intent = Intent(this,MainActivity3::class.java)
            startActivity(intent)
        }
        mousebutton.setOnClickListener{
            var intent:Intent=Intent(this,MainActivity4::class.java)
            startActivity(intent)
        }
        sharebutton.setOnClickListener {
//            var intent = Intent(this,MainActivity5::class.java)
//            startActivity(intent)
            var url = "http://"+ip+":1828";
            var intent = Intent(Intent.ACTION_VIEW,Uri.parse(url))
            startActivity(intent)
        }
    }
}