package com.example.screendesign.ui.configuration

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.screendesign.ui.home.HomeViewModel

class ConfigurationViewModelFactory (
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConfigurationViewModel(
            context = context
        ) as T
    }
}