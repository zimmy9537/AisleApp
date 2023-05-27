package com.zimmy.sample.aisleapp.ui.main.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.zimmy.sample.aisleapp.databinding.ActivityOtpBinding
import com.zimmy.sample.aisleapp.network.ResultData
import com.zimmy.sample.aisleapp.ui.main.home.HomeActivity
import com.zimmy.sample.aisleapp.ui.main.login.viewmodel.LoginViewModel
import com.zimmy.sample.aisleapp.util.AppPreference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpBinding

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var phoneNumber: String

    private lateinit var appPreference: AppPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setObserver()
        initialise()
    }

    private fun setObserver() {
        viewModel.tokenData.observe(this) {
            when (it) {
                is ResultData.Loading -> {
                    binding.progressBg.visibility = View.VISIBLE
                }

                is ResultData.Success -> {
                    binding.progressBg.visibility = View.GONE
                    Toast.makeText(this, "Otp successfully verified", Toast.LENGTH_SHORT).show()
                    val token = it.data?.token
                    if (token != null) {
                        appPreference.saveString(AppPreference.AUTH_KEY, token)
                        val intent = Intent(this, HomeActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
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
        phoneNumber = intent.getStringExtra("PhoneNumber").toString()
        binding.phoneNumberTv.text = phoneNumber
        binding.continueCl.setOnClickListener {
            if (binding.otpEt.text.isEmpty()) {
                Toast.makeText(this@OtpActivity, "Please Enter Otp", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val otp = binding.otpEt.text.toString()
            viewModel.verifyOtp(phoneNumber, otp)
        }
        binding.editOtp.setOnClickListener {
            finish()
        }
        appPreference = AppPreference(this@OtpActivity)
    }
}