package com.example.screendesign.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.screendesign.R
import com.example.screendesign.databinding.ActivityLoginBinding
import com.example.screendesign.fragment.LoginFragment
import com.example.screendesign.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity(){

    private lateinit var binding:ActivityLoginBinding

    fun resetPassword(view: android.view.View) {
        view.setOnClickListener {
            startActivity(Intent(applicationContext,PasswordResettingActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }
    }
}