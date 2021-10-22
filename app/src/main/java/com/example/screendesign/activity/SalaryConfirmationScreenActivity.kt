package com.example.screendesign.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.screendesign.R
import com.example.screendesign.databinding.ActivitySalaryConfirmationScreenBinding

class SalaryConfirmationScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySalaryConfirmationScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_salary_confirmation_screen)


    }
}