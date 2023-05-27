package com.dicoding.picodiploma.storyapptest1.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.dicoding.picodiploma.storyapptest1.Constant
import com.dicoding.picodiploma.storyapptest1.data.Result
import com.dicoding.picodiploma.storyapptest1.preferences.AuthPreferences
import com.dicoding.picodiploma.storyapptest1.ui.main.MainActivity
import com.dicoding.picodiploma.storyapptest1.databinding.ActivityLoginBinding
import com.dicoding.picodiploma.storyapptest1.network.ApiConfig
import com.dicoding.picodiploma.storyapptest1.network.model.User
import com.dicoding.picodiploma.storyapptest1.network.response.LoginResponse
import com.dicoding.picodiploma.storyapptest1.ui.maps.MapsViewModel
import com.dicoding.picodiploma.storyapptest1.ui.maps.MapsViewModelFactory
import com.dicoding.picodiploma.storyapptest1.ui.register.RegisterActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var preferences: AuthPreferences
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = AuthPreferences(this)

        initAction()
        playAnimation()

        binding.tvToRegister.setOnClickListener {
            val myIntent = Intent(this, RegisterActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }

    private fun initAction() {
        binding.btnLogin.setOnClickListener {
            handle()
        }
    }

    fun handle() {
        viewModel.getLogin(
            email = binding.etEmail.text.toString(),
            password = binding.etPass.text.toString()
        ).observe(this, { response ->
            if (response != null) {
                when (response) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val user = User(
                            response.data.loginResult.name.toString(),
                            response.data.loginResult.email.toString(),
                            response.data.loginResult.userId.toString(),
                            response.data.loginResult.token.toString()
                        )
                        preferences.setUser(user)
                        preferences.setStatusLogin(true)
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                        Toast.makeText(applicationContext, "Login Berhasil", Toast.LENGTH_SHORT)
                            .show()

                    }
                    is Result.Failure -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun playAnimation() {
        CoroutineScope(Dispatchers.IO).launch {

            val txtEmail =
                ObjectAnimator.ofFloat(binding.txtEmail, View.ALPHA, 1f).setDuration(500)
            val etEmail = ObjectAnimator.ofFloat(binding.etEmail, View.ALPHA, 1f).setDuration(500)
            val txtPass = ObjectAnimator.ofFloat(binding.txtPass, View.ALPHA, 1f).setDuration(500)
            val etPass = ObjectAnimator.ofFloat(binding.etPass, View.ALPHA, 1f).setDuration(500)
            val btnLogin =
                ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(500)


            AnimatorSet().apply {
                playSequentially(txtEmail, etEmail, txtPass, etPass, btnLogin)
                Handler(Looper.getMainLooper()).postDelayed({
                    start()
                }, Constant.ANIMATION_TIME);
            }
        }
    }
}