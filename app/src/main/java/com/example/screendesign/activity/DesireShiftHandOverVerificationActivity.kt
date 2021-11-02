package com.example.screendesign.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.screendesign.R
import com.example.screendesign.data.ShiftDate
import com.example.screendesign.fragment.DesireShiftHandOverFragment
import com.example.screendesign.fragment.DesireShiftHandOverVerificationFragment

class DesireShiftHandOverVerificationActivity : AppCompatActivity() ,DesireShiftHandOverVerificationFragment.Callback{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.desire_shift_hand_over_verification_activity)

        if (savedInstanceState == null) {
            val bundle = Bundle()
            val file = intent.getSerializableExtra("shiftList")
            bundle.putSerializable("FILE_KEY", file)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DesireShiftHandOverVerificationFragment.newInstance().apply { this.arguments = bundle })
                .commitNow()
        }
    }

    override fun clickFixBtn(){
        val intent = Intent().apply {
            putExtra("intent",intent.getSerializableExtra("shiftList"))
        }
        setResult(Activity.RESULT_OK,intent)
        finish()
    }
}