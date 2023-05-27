package com.dicoding.picodiploma.storyapptest1.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.dicoding.picodiploma.storyapptest1.Constant
import com.dicoding.picodiploma.storyapptest1.preferences.AuthPreferences
import com.dicoding.picodiploma.storyapptest1.ui.main.MainActivity
import com.dicoding.picodiploma.storyapptest1.databinding.ActivitySplashBinding
import com.dicoding.picodiploma.storyapptest1.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var pref: AuthPreferences
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        moveToMainActivity()
    }

    private fun moveToMainActivity() {
        pref = AuthPreferences(this)
        Handler(Looper.getMainLooper()).postDelayed({
            if (pref.getStatusLogin()) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }, Constant.SPLASH_TIME)
    }
}