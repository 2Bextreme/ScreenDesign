package com.example.screendesign.viewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.screendesign.viewmodel.ConfirmationAlreadyShiftHandOverViewModel

class ConfirmationAlreadyShiftHandOverViewModelFactory (
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConfirmationAlreadyShiftHandOverViewModel(
            context = context
        ) as T
    }
}