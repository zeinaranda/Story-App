package com.dicoding.picodiploma.storyapptest1.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.storyapptest1.ui.post.PostActivity
import com.dicoding.picodiploma.storyapptest1.R
import com.dicoding.picodiploma.storyapptest1.adapter.LoadingStateAdapter
import com.dicoding.picodiploma.storyapptest1.databinding.ActivityMainBinding
import com.dicoding.picodiploma.storyapptest1.preferences.AuthPreferences
import com.dicoding.picodiploma.storyapptest1.ui.profile.ProfilActivity
import com.dicoding.picodiploma.storyapptest1.adapter.StoryAdapter
import com.dicoding.picodiploma.storyapptest1.ui.maps.MapsActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var preferences: AuthPreferences
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory.getInstanceStory(this)
    }
    private var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = AuthPreferences(this)

        binding.rvUsers.layoutManager = LinearLayoutManager(this)

        token = preferences.getToken()


        val adapter = StoryAdapter()
        binding.rvUsers.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        lifecycleScope.launch {
            viewModel.getStory("Bearer $token").observe(this@MainActivity) {
                adapter.submitData(lifecycle, it)
            }
        }

        binding.fabAdd.setOnClickListener { view ->
            if (view.id == R.id.fab_add) {
                val intent = Intent(this@MainActivity, PostActivity::class.java)
                intent.putExtra(PostActivity.TOKEN, preferences.getToken())
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottom_nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu1 -> {
                val i = Intent(this, ProfilActivity::class.java)
                startActivity(i)
                finish()
                true
            }
            R.id.menu2 -> {
                val i = Intent(this, MapsActivity::class.java)
                startActivity(i)
                finish()
                true
            }
            else -> true
        }
    }
}