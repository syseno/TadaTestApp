package com.example.tadatest.ui.login

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tadatest.MainActivity
import com.example.tadatest.R
import com.example.tadatest.base.BaseActivity
import com.example.tadatest.databinding.ActivityLoginBinding
import com.example.tadatest.prefs.PrefManager
import com.example.tadatest.ui.register.RegisterActivity
import com.example.tadatest.utils.makeLinks
import org.koin.androidx.viewmodel.ext.android.getViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun getViewBinding() = ActivityLoginBinding.inflate(layoutInflater)

    override fun getInjectViewModel(): LoginViewModel = getViewModel()

    override fun setupViews() {
        renderTextRegister()
        binding.buttonLogin.setOnClickListener {
            validateLogin()
        }
    }

    private fun renderTextRegister() {
        binding.textRegister.makeLinks(
            Pair(getString(R.string.feature_register_title), View.OnClickListener {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            })
        )
    }

    private fun validateLogin() {
        if (binding.inputUsername.text.toString().isBlank()) {
            binding.inputUsername.error = getString(R.string.msg_error_user_name_not_filled)
        }
        if (binding.inputPassword.text.toString().isBlank()) {
            binding.inputPassword.error = getString(R.string.msg_error_password_not_filled)
        }
        if (binding.inputUsername.text.toString() == PrefManager.getInstance(this).userName
            && binding.inputPassword.text.toString() == PrefManager.getInstance(this).userPassword
        ) {
            PrefManager.getInstance(this).isLogin = true
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(
                this,
                R.string.msg_error_username_and_password_not_match,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}