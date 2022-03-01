package com.example.tadatest.ui.register

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.tadatest.MainActivity
import com.example.tadatest.R
import com.example.tadatest.base.BaseActivity
import com.example.tadatest.databinding.ActivityLoginBinding
import com.example.tadatest.databinding.ActivityRegisterBinding
import com.example.tadatest.prefs.PrefManager
import com.example.tadatest.ui.login.LoginActivity
import com.example.tadatest.ui.login.LoginViewModel
import com.example.tadatest.utils.makeLinks
import org.koin.androidx.viewmodel.ext.android.getViewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>() {
    override fun getViewBinding() = ActivityRegisterBinding.inflate(layoutInflater)

    override fun getInjectViewModel(): RegisterViewModel = getViewModel()

    override fun setupViews() {
        renderTextLogin()
        binding.buttonRegister.setOnClickListener {
            validateRegister()
        }
    }

    private fun renderTextLogin() {
        binding.textLogin.makeLinks(
            Pair(getString(R.string.feature_login_title), View.OnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            })
        )
    }

    private fun validateRegister() {
        if (binding.inputUsername.text.toString().isBlank()) {
            binding.inputUsername.error = getString(R.string.msg_error_user_name_not_filled)
        }
        if (binding.inputPassword.text.toString().isBlank()) {
            binding.inputPassword.error = getString(R.string.msg_error_password_not_filled)
        }
        if (binding.cbTnc.isChecked.not()) {
            binding.cbTnc.error = getString(R.string.msg_error_checkbox_tnc_not_checked)
        }
        if (binding.inputUsername.text.toString().isBlank()
                .not() && binding.inputUsername.text.toString().isBlank()
                .not() && binding.cbTnc.isChecked
        ) {
            PrefManager.getInstance(this).userName = binding.inputUsername.text.toString()
            PrefManager.getInstance(this).userPassword = binding.inputPassword.text.toString()
            PrefManager.getInstance(this).isLogin = true
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}