package com.example.screendesign.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.screendesign.R
import com.example.screendesign.fragment.DesireShiftHandOverFragment
import com.example.screendesign.fragment.DesireShiftHandOverVerificationFragment

class DesireShiftHandOverVerificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.desire_shift_hand_over_verification_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DesireShiftHandOverVerificationFragment.newInstance())
                .commitNow()
        }
    }
}