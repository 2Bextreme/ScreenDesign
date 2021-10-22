package com.example.screendesign.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.screendesign.R
import com.example.screendesign.fragment.DesireShiftHandOverFragment
import com.example.screendesign.databinding.DesireShiftHandOverFragmentBinding

class DesireShiftHandOverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.desire_shift_hand_over_activity)
        Log.d("a","アクティビティ")
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DesireShiftHandOverFragment.newInstance())
                .commitNow()
        }
    }
}