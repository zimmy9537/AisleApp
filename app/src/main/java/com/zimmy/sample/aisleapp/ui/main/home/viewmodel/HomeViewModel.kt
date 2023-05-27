package com.zimmy.sample.aisleapp.ui.main.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zimmy.sample.aisleapp.model.Profile
import com.zimmy.sample.aisleapp.model.Profiles
import com.zimmy.sample.aisleapp.model.StatusModel
import com.zimmy.sample.aisleapp.network.ResultData
import com.zimmy.sample.aisleapp.usecase.AisleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val aisleUseCase: AisleUseCase) : ViewModel() {

    private val _profilesData: MutableLiveData<ResultData<Profiles>> = MutableLiveData()
    val profilesData: LiveData<ResultData<Profiles>>
        get() = _profilesData


    fun getTestProfiles(authKey: String) {
        viewModelScope.launch {
            aisleUseCase.getTestProfiles(authKey).onEach {
                _profilesData.postValue(it)
            }.collect()
        }
    }
}