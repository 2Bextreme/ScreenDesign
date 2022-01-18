package com.example.screendesign.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.screendesign.R
import com.example.screendesign.databinding.ActivityPasswordResettingBinding
import com.example.screendesign.repository.Repository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PasswordResettingActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPasswordResettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = Repository(applicationContext)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_password_resetting)
        binding.ressettiongSendMailBtn.setOnClickListener {
            val text = binding.passwordResettingMailaddressEditText
            CoroutineScope(Dispatchers.IO).launch {
                Log.d("id",binding.editTextTextPersonName2.text.toString())
                Log.d("mailAddress",binding.passwordResettingMailaddressEditText.text.toString())
                val ret = repository.forgetPassword(
                    binding.editTextTextPersonName2.text.toString(),
                    binding.passwordResettingMailaddressEditText.text.toString()
                ).result
                if (ret){
                    Snackbar.make(binding.root,"${text.text}宛に再設定用メールを送信しました",Snackbar.LENGTH_SHORT).show()
                }else{
                    Snackbar.make(binding.root,"エラーが発生しました",Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}