package com.example.screendesign.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.screendesign.R
import com.example.screendesign.activity.TopPageActivity
import com.example.screendesign.databinding.LoginFragmentBinding
import com.example.screendesign.viewmodel.LoginViewModel
import com.example.screendesign.viewmodelfactory.LoginViewModelFactory
import kotlinx.coroutines.*

class LoginFragment : Fragment(){

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var binding: LoginFragmentBinding
    private lateinit var viewModel:LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.login_fragment, container, false
        )

        //viewModelを使うためのセット的なもの
        viewModel = ViewModelProvider(this, LoginViewModelFactory(requireContext()))[LoginViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        //

        //入力フォームの監視
        viewModel.loginState.observe(viewLifecycleOwner, Observer {
            val loginState = it ?: return@Observer

            binding.login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                binding.username.error = loginState.usernameError
            }
            if (loginState.passwordError != null) {
                binding.password.error = loginState.passwordError
            }
        })
        //

        binding.login.setOnClickListener {
            viewModel.login(openDetailListener)
        }

        binding.username.afterTextChanged {
            viewModel.loginDataChanged(
                binding.username.text.toString(),
                binding.password.text.toString()
            )
        }

        binding.password.apply {
            afterTextChanged {
                viewModel.loginDataChanged(

                    binding.username.text.toString(),
                    binding.password.text.toString()
                )
            }
        }

        return binding.root
    }

    private val openDetailListener = object : LoginViewModel.Callback {
        override fun moveTopPage() {
            Toast.makeText(requireContext(), "ようこそ", Toast.LENGTH_SHORT).show()
            startActivity(Intent(requireContext(), TopPageActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        }

        override fun toast() {
            Toast.makeText(requireContext(), "無効なログインです", Toast.LENGTH_SHORT).show()
        }
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}