package com.example.screendesign.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ToMonthShitList(
    val year :Int,
    val month:Int,
    val days:List<Int>
): Parcelable