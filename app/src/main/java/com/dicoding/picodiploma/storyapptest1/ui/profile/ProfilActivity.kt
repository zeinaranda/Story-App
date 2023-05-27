package com.dicoding.picodiploma.storyapptest1.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.dicoding.picodiploma.storyapptest1.preferences.AuthPreferences
import com.dicoding.picodiploma.storyapptest1.R
import com.dicoding.picodiploma.storyapptest1.databinding.ActivityProfilBinding
import com.dicoding.picodiploma.storyapptest1.ui.login.LoginActivity
import com.dicoding.picodiploma.storyapptest1.ui.main.MainActivity

class ProfilActivity : AppCompatActivity() {
    private lateinit var preferences: AuthPreferences
    private lateinit var binding: ActivityProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        preferences = AuthPreferences(this)
        getProfile()
        initAction()
    }

    private fun initAction() {
        binding.btnBahasa.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }
        binding.btnLogout.setOnClickListener {
            getLogout()
            this.finish()
        }
        binding.toolbar.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun getProfile(){
        binding.tvItemName.text = preferences.getUser().name
    }

    private fun getLogout() {
        preferences.logout()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}