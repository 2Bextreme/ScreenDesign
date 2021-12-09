package com.example.screendesign.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.screendesign.R
import com.example.screendesign.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MailAddressChangeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mail_address_change)
        val repository = Repository(applicationContext)

        val button : Button = findViewById(R.id.changeMailAddressSendBtn)

        button.setOnClickListener {
            button.isEnabled = false
            val text:TextView = findViewById(R.id.changeMailAddressEditText)
            Log.d("sendMailAddress",text.text.toString())
            CoroutineScope(Dispatchers.IO).launch {
                repository.changeMailAddress(text.text.toString())
            }
            Toast.makeText(applicationContext, "${text.text}にメールアドレスを変更しました", Toast.LENGTH_LONG).show()
            button.isEnabled = true
        }
    }
}