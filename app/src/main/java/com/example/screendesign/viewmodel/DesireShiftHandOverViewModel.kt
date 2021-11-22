package com.example.screendesign.viewmodel

import androidx.lifecycle.ViewModel
import com.example.screendesign.data.ShiftDate
import kotlin.collections.ArrayList

class DesireShiftHandOverViewModel : ViewModel() {
    val shiftDate = ArrayList<ShiftDate>()
}