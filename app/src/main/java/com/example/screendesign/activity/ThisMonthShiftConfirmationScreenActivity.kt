package com.example.screendesign.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.screendesign.R
import com.example.screendesign.fragment.ThisMonthShiftConfirmationScreenFragment

class ThisMonthShiftConfirmationScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.this_month_shift_confirmation_screen_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ThisMonthShiftConfirmationScreenFragment.newInstance())
                .commitNow()
        }
    }
}