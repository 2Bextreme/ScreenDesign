package com.example.screendesign.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.screendesign.R
import com.example.screendesign.databinding.ActivityPasswordResettingBinding
import com.example.screendesign.viewmodel.PasswordResettingViewModel
import com.example.screendesign.viewmodelfactory.PasswordResettingViewModelFactory
import com.google.android.material.snackbar.Snackbar

class PasswordResettingActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPasswordResettingBinding
    private lateinit var viewModel: PasswordResettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_password_resetting)

        viewModel = ViewModelProvider(
            this, PasswordResettingViewModelFactory(applicationContext)
        )[PasswordResettingViewModel::class.java]

        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        binding.ressettiongSendMailBtn.setOnClickListener {
            when{
                !viewModel.empId.value.isNullOrEmpty() && !viewModel.mailAddress.value.isNullOrEmpty()
                ->viewModel.resetPass()

                viewModel.empId.value.isNullOrEmpty() ->binding.editTextTextPersonName2.error = "従業員IDを入力してください"
                viewModel.mailAddress.value.isNullOrEmpty()->binding.passwordResettingMailaddressEditText.error="メールアドレスを入力してください"
            }
        }

        viewModel.isLog.observe(this,{
            binding.ressettiongSendMailBtn.isEnabled = it != 1
            when(it){
                0 ->Snackbar.make(binding.root,"${viewModel.mailAddress.value}宛に再設定用メールを送信しました", Snackbar.LENGTH_SHORT).show()
                2 ->Snackbar.make(binding.root,"エラーが発生しました", Snackbar.LENGTH_SHORT).show()
            }
        })
    }
}