package com.zimmy.sample.aisleapp.ui.main.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zimmy.sample.aisleapp.model.StatusModel
import com.zimmy.sample.aisleapp.model.TokenModel
import com.zimmy.sample.aisleapp.network.ResultData
import com.zimmy.sample.aisleapp.usecase.AisleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val aisleUseCase: AisleUseCase) : ViewModel() {

    private val _statusData: MutableLiveData<ResultData<StatusModel>> = MutableLiveData()
    val status: LiveData<ResultData<StatusModel>>
        get() = _statusData

    private val _tokenData: MutableLiveData<ResultData<TokenModel>> = MutableLiveData()
    val tokenData: LiveData<ResultData<TokenModel>>
        get() = _tokenData


    fun performPhoneLogin(number: String) {
        viewModelScope.launch {
            aisleUseCase.performPhoneLogin(number).onEach {
                _statusData.postValue(it)
            }.collect()
        }
    }

    fun verifyOtp(number: String, otp: String) {
        viewModelScope.launch {
            aisleUseCase.verifyOtp(number, otp).onEach {
                _tokenData.postValue(it)
            }.collect()
        }
    }

}