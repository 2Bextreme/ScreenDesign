package com.example.screendesign.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.screendesign.R
import com.example.screendesign.databinding.ActivityPasswordChangeBinding
import com.example.screendesign.viewmodel.PasswordChangeViewModel
import com.example.screendesign.viewmodelfactory.PasswordChangeViewModelFactory
import com.google.android.material.snackbar.Snackbar

class PasswordChangeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPasswordChangeBinding
    private lateinit var viewModel:PasswordChangeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_password_change)

        //viewModelを使うためのセット的なもの
        viewModel = ViewModelProvider(this, PasswordChangeViewModelFactory(applicationContext))[PasswordChangeViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        //

        binding.passwordChangeConfirmBtn.isEnabled = false

        viewModel.newPass1.observe(this, {
            viewModel.notGoodSizePass1 = (viewModel.newPass1.value?.length ?:0) <= 5
            viewModel.notGoodSizePass2 = (viewModel.newPass2.value?.length ?:0) <= 5
            viewModel.notMatchPass = viewModel.newPass1.value == viewModel.newPass2.value
            if(viewModel.notGoodSizePass1){
                binding.editTextTextPassword3.error = "パスワードが短すぎます。"
            }
            if(viewModel.notGoodSizePass2){
                binding.editTextTextPassword2.error = "パスワードが短すぎます。"
            }
            binding.passwordChangeConfirmBtn.isEnabled = !(viewModel.notGoodSizePass1 || viewModel.notGoodSizePass2)
        })

        viewModel.newPass2.observe(this, {
            viewModel.notGoodSizePass1 = (viewModel.newPass1.value?.length ?:0) <= 5
            viewModel.notGoodSizePass2 = (viewModel.newPass2.value?.length ?:0) <= 5
            viewModel.notMatchPass = viewModel.newPass1.value == viewModel.newPass2.value
            if(viewModel.notGoodSizePass1){
                binding.editTextTextPassword3.error = "パスワードが短すぎます。"
            }
            if(viewModel.notGoodSizePass2){
                binding.editTextTextPassword2.error = "パスワードが短すぎます。"
            }
            binding.passwordChangeConfirmBtn.isEnabled = !(viewModel.notGoodSizePass1 || viewModel.notGoodSizePass2)
        })

        viewModel.isLog.observe(this,{
            binding.passwordChangeConfirmBtn.isEnabled = it != 98
            Log.d("observe",it.toString())
            runSnackBar(it)
            if(it == 1){
                startActivity(Intent(this,TopPageActivity::class.java))
            }
        })

        binding.passwordChangeConfirmBtn.setOnClickListener {
            viewModel.passwordChange()
        }
    }

    private fun runSnackBar(log:Int){
        when(log){
            1 -> Snackbar.make(binding.root, "パスワードを変更しました。", Snackbar.LENGTH_SHORT).show()
            2 -> Snackbar.make(binding.root, "パスワードが一致していません。", Snackbar.LENGTH_SHORT).show()
            3 -> Snackbar.make(binding.root, "現在のパスワードを入力してください。", Snackbar.LENGTH_SHORT).show()
            98 -> return
            else -> Snackbar.make(binding.root, "何らかのエラーが発生しました。", Snackbar.LENGTH_SHORT).show()
        }
    }
}