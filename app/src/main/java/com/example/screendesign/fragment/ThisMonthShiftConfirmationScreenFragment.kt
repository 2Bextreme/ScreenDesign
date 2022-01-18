package com.example.screendesign.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.screendesign.R
import com.example.screendesign.viewmodel.ThisMonthShiftConfirmationScreenViewModel
import java.util.*

class ThisMonthShiftConfirmationScreenFragment : Fragment() {

    companion object {
        fun newInstance() = ThisMonthShiftConfirmationScreenFragment()
    }

    private lateinit var viewModel: ThisMonthShiftConfirmationScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[ThisMonthShiftConfirmationScreenViewModel::class.java]



        return inflater.inflate(
            R.layout.this_month_shift_confirmation_screen_fragment,
            container,
            false
        )
    }
}