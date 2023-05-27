package com.dicoding.picodiploma.storyapptest1.ui.register

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.dicoding.picodiploma.storyapptest1.Constant
import com.dicoding.picodiploma.storyapptest1.data.Result
import com.dicoding.picodiploma.storyapptest1.databinding.ActivityRegisterBinding
import com.dicoding.picodiploma.storyapptest1.ui.login.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels {
        RegisterViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAction()
        playAnimation()
        binding.tvToLogin.setOnClickListener {
            val myIntent = Intent(this, LoginActivity::class.java)
            startActivity(myIntent)
        }
    }

    fun initAction() {
        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    fun register() {

        viewModel.getRegist(
            name = binding.etNama.text.toString(),
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
                        val myIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(myIntent)
                        Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    }
                    is Result.Failure -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, "Register Gagal", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun playAnimation() {
        CoroutineScope(Dispatchers.IO).launch {

            val txtName = ObjectAnimator.ofFloat(binding.txtName, View.ALPHA, 1f).setDuration(500)
            val etName = ObjectAnimator.ofFloat(binding.etNama, View.ALPHA, 1f).setDuration(500)
            val txtEmail = ObjectAnimator.ofFloat(binding.txtEmail, View.ALPHA, 1f).setDuration(500)
            val etEmail = ObjectAnimator.ofFloat(binding.etEmail, View.ALPHA, 1f).setDuration(500)
            val txtPass = ObjectAnimator.ofFloat(binding.txtPass, View.ALPHA, 1f).setDuration(500)
            val etPass = ObjectAnimator.ofFloat(binding.etPass, View.ALPHA, 1f).setDuration(500)
            val btnRegister =
                ObjectAnimator.ofFloat(binding.btnRegister, View.ALPHA, 1f).setDuration(500)

            AnimatorSet().apply {

                playSequentially(
                    txtName,
                    etName,
                    txtEmail,
                    etEmail,
                    txtPass,
                    etPass,
                    btnRegister
                )
                Handler(Looper.getMainLooper()).postDelayed({

                    start()
                }, Constant.ANIMATION_TIME);
            }
        }
    }
}