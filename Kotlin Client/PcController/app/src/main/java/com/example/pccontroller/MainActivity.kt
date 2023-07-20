package com.example.pccontroller

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.DataOutputStream
import java.lang.Exception
import java.net.Socket
var ip : String="";
lateinit var con:connection
class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         con =connection();
        var text=findViewById<TextView>(R.id.txtview);
        var inputxtip = findViewById<EditText>(R.id.editText)
        var portno = findViewById<EditText>(R.id.portn)
        var button = findViewById<Button>(R.id.b1)

        var port:Int;
        var intent =Intent(this,MainActivity2::class.java)
        button.setOnClickListener{
           ip= inputxtip.getText().toString()
            port= Integer.parseInt(portno.getText().toString())
            GlobalScope.launch {
                try {
                    text.text="trying to connect...."
                    con.connect(ip, port)
                    startActivity(intent)
                } catch (e: Exception) {
                    text.text = e.message
                }

            }
        }




    }

}
class connection{
    lateinit var dataOutput:DataOutputStream;
    lateinit var sock:Socket;
    suspend fun connect(ip:String,port :Int)
    {
        sock=Socket(ip,port);
        dataOutput= DataOutputStream(sock.getOutputStream())

    }
    fun sendcommnad(str:String){
        dataOutput.writeUTF(str)
    }

}