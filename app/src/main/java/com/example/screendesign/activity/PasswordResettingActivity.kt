package com.example.screendesign.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.screendesign.R
import com.example.screendesign.databinding.ActivityMailAddressChangeBinding
import com.example.screendesign.databinding.ActivityPasswordResettingBinding

class PasswordResettingActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPasswordResettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_password_resetting)
        binding.ressettiongSendMailBtn.setOnClickListener {
            val text = binding.passwordResettingMailaddressEditText
            Toast.makeText(applicationContext, "${text.text}宛に再設定用メールを送信しました", Toast.LENGTH_LONG).show()
        }
    }
}