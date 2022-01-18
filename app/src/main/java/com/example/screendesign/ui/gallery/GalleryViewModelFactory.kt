package com.example.screendesign.ui.gallery

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.screendesign.ui.home.HomeViewModel

class GalleryViewModelFactory (
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GalleryViewModel(
            context = context
        ) as T
    }
}