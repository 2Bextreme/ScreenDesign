package com.example.screendesign.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.screendesign.R
import com.example.screendesign.databinding.ActivityLoginBinding
import com.example.screendesign.fragment.LoginFragment
import com.example.screendesign.viewmodel.LoginViewModel
import com.example.screendesign.viewmodelfactory.LoginViewModelFactory

class LoginActivity : AppCompatActivity(){

    private lateinit var binding:ActivityLoginBinding
    private lateinit var viewModel:LoginViewModel

    fun resetPassword(notUse:View) {
        startActivity(Intent(applicationContext,PasswordResettingActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, LoginViewModelFactory(applicationContext))[LoginViewModel::class.java]

        viewModel.loginCheck()
        //ログインチェック
        viewModel.loginCheck.observe(this, {
            Log.d("loginCheck",it.toString())
            if (!it) {
                if (viewModel.isLog.value == 1) {
                    startActivity(
                        Intent(applicationContext, TopPageActivity::class.java)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    )
                } else {
                    if (savedInstanceState == null) {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, LoginFragment.newInstance())
                            .commitNow()
                    }
                }
            }
        })
    }
}