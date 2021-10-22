package com.example.screendesign.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.screendesign.R
import com.example.screendesign.databinding.ActivityConfirmationAlreadyShiftHandOverBinding

class ConfirmationAlreadyShiftHandOverActivity : AppCompatActivity() {

    private lateinit var binding :ActivityConfirmationAlreadyShiftHandOverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_confirmation_already_shift_hand_over
        )
    }
}