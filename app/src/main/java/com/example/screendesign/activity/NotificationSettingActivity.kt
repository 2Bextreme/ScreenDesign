package com.example.screendesign.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.screendesign.databinding.NotificationSettingActivityBinding

class NotificationSettingActivity : AppCompatActivity() {

    private lateinit var binding:NotificationSettingActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NotificationSettingActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.notificationConfirmBtn.setOnClickListener {
            Log.d("switch",binding.switch1.isChecked.toString())
//            startActivity(Intent(applicationContext,TopPageActivity::class.java))
        }
    }
}