package com.example.screendesign.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.Month
import java.time.Year

@Parcelize
data class ShiftDate(
    val year : String,
    val month: String,
    val day  : String,
    var isCheck:Boolean
) :Parcelable