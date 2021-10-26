package com.example.screendesign.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.screendesign.R

class MailAddressChangeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mail_address_change)

        val button : Button = findViewById(R.id.changeMailAddressSendBtn)

        button.setOnClickListener {
            val text:TextView = findViewById(R.id.changeMailAddressEditText)
            Toast.makeText(applicationContext, "${text.text}宛にメールを送信しました", Toast.LENGTH_LONG).show()
        }
    }
}