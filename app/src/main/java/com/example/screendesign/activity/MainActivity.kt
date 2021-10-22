package com.example.screendesign.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.screendesign.R
import com.example.screendesign.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.button1.setOnClickListener {
            startActivity(Intent(applicationContext, DesireShiftHandOverActivity::class.java))
        }

        binding.button2.setOnClickListener {
            startActivity(Intent(applicationContext, ConfirmationAlreadyShiftHandOverActivity::class.java))
        }

        binding.button3.setOnClickListener {
            startActivity(Intent(applicationContext, ThisMonthShiftConfirmationScreenActivity::class.java))
        }

        binding.button4.setOnClickListener {
            startActivity(Intent(applicationContext, SalaryConfirmationScreenActivity::class.java))
        }

        binding.button5.setOnClickListener {
            startActivity(Intent(applicationContext, ConfigurationActivity::class.java))
        }
    }
}