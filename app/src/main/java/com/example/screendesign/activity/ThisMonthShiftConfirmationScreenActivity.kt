package com.example.screendesign.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.screendesign.R
import com.example.screendesign.databinding.ActivityThisMonthShiftConfirmationScreenBinding

class ThisMonthShiftConfirmationScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivityThisMonthShiftConfirmationScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_this_month_shift_confirmation_screen
        )
    }
}