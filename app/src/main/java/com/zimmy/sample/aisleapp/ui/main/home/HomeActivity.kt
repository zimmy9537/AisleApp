package com.zimmy.sample.aisleapp.ui.main.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.commit
import com.zimmy.sample.aisleapp.R
import com.zimmy.sample.aisleapp.databinding.ActivityHomeBinding
import com.zimmy.sample.aisleapp.network.ResultData
import com.zimmy.sample.aisleapp.ui.main.home.fragment.DiscoverFragment
import com.zimmy.sample.aisleapp.ui.main.home.fragment.MatchesFragment
import com.zimmy.sample.aisleapp.ui.main.home.fragment.NotesFragment
import com.zimmy.sample.aisleapp.ui.main.home.fragment.ProfileFragment
import com.zimmy.sample.aisleapp.ui.main.home.viewmodel.HomeViewModel
import com.zimmy.sample.aisleapp.ui.main.login.viewmodel.LoginViewModel
import com.zimmy.sample.aisleapp.util.AppPreference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val viewModel: HomeViewModel by viewModels()
    val TAG = HomeActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setObserver()
        initialise()

    }

    private fun setObserver() {
        viewModel.profilesData.observe(this) {
            when (it) {
                is ResultData.Loading -> {
                    binding.progressPb.visibility = View.VISIBLE
                }

                is ResultData.Success -> {
                    binding.progressPb.visibility = View.GONE
                    Log.d(TAG, "VALUES FROM API call:- ${it.data}")
                    Toast.makeText(this, "Values fetched in LOGCAT", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    binding.progressPb.visibility = View.GONE
                    Toast.makeText(this@HomeActivity, "Something went wrong", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun initialise() {
        val appPreference = AppPreference(this@HomeActivity)
        val token = appPreference.getString(AppPreference.AUTH_KEY)
        if (token != null) {
            viewModel.getTestProfiles(token)
        }
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_discover -> {
                    supportFragmentManager.commit {
                        replace(binding.container.id, DiscoverFragment.newInstance())
                    }
                }

                R.id.navigation_notes -> {
                    supportFragmentManager.commit {
                        replace(binding.container.id, NotesFragment.newInstance())
                    }
                }

                R.id.navigation_matches -> {
                    supportFragmentManager.commit {
                        replace(binding.container.id, MatchesFragment.newInstance())
                    }
                }

                R.id.navigation_profile -> {
                    supportFragmentManager.commit {
                        replace(binding.container.id, ProfileFragment.newInstance())
                    }
                }
            }
            true
        }
    }
}