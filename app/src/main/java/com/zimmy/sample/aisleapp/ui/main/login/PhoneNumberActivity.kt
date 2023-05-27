package com.zimmy.sample.aisleapp.ui.main.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.zimmy.sample.aisleapp.databinding.ActivityPhoneNumberBinding
import com.zimmy.sample.aisleapp.network.ResultData
import com.zimmy.sample.aisleapp.ui.main.home.HomeActivity
import com.zimmy.sample.aisleapp.ui.main.login.viewmodel.LoginViewModel
import com.zimmy.sample.aisleapp.util.AppPreference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneNumberActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhoneNumberBinding

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var phoneNumber: String
    private lateinit var code: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setObserver()
        initialise()
    }

    private fun setObserver() {
        viewModel.status.observe(this) {
            when (it) {
                is ResultData.Loading -> {
                    binding.progressBg.visibility = View.VISIBLE
                }

                is ResultData.Success -> {
                    binding.progressBg.visibility = View.GONE
                    if (it.data?.status != true) {
                        Toast.makeText(this, "Unauthorised Access", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Otp sent successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@PhoneNumberActivity, OtpActivity::class.java)
                        val finalPhoneNumber = code + phoneNumber
                        intent.putExtra("PhoneNumber", finalPhoneNumber)
                        startActivity(intent)
                    }
                }

                else -> {
                    binding.progressBg.visibility = View.GONE
                    Toast.makeText(this, "Please try again later", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initialise() {
        val appPreference = AppPreference(this)
        val token = appPreference.getString(AppPreference.AUTH_KEY)
        if (!token.isNullOrEmpty()) {
            val intent = Intent(this@PhoneNumberActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.continueCl.setOnClickListener {
            if (binding.codeEt.text.isEmpty()) {
                Toast.makeText(this, "Phone number code is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (binding.phoneNumherEt.text.isEmpty()) {
                Toast.makeText(this, "Phone number is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            code = binding.codeEt.text.toString()
            phoneNumber = binding.phoneNumherEt.text.toString()
            val finalPhoneNumber = code + phoneNumber
            viewModel.performPhoneLogin(finalPhoneNumber)
        }
    }
}