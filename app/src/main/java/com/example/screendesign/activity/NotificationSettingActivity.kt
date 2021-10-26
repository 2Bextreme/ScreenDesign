package com.example.screendesign.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.example.screendesign.R

class NotificationSettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notification_setting_activity)

        val confirmBtn:Button = findViewById(R.id.notificationConfirmBtn)

        confirmBtn.setOnClickListener {
            startActivity(Intent(applicationContext,TopPageActivity::class.java))
        }
    }
}