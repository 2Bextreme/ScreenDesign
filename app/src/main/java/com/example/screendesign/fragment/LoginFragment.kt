package com.example.screendesign.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.screendesign.R
import com.example.screendesign.databinding.LoginFragmentBinding

import com.example.screendesign.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding  : LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.login_fragment, container, false)

        //viewModelを使うためのセット的なもの
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        //

        binding.login.setOnClickListener {
//            startActivity(Intent(requireContext(), TopPageActivity::class.java))
            viewModel.login()
        }

        return binding.root
    }
}