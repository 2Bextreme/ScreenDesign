package com.example.screendesign.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.screendesign.R

class PasswordChangeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_change)

        val button:Button = findViewById(R.id.passwordChangeConfirmBtn)
        button.setOnClickListener {
            startActivity(Intent(applicationContext,TopPageActivity::class.java))
        }
    }
}