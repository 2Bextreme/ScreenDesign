package com.example.screendesign.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.screendesign.R
import com.example.screendesign.fragment.ConfirmationAlreadyShiftHandOverFragment

class ConfirmationAlreadyShiftHandOverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.confirmation_already_shift_hand_over_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ConfirmationAlreadyShiftHandOverFragment.newInstance())
                .commitNow()
        }
    }
}