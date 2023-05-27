package com.zimmy.sample.aisleapp.repository

import com.zimmy.sample.aisleapp.model.Profiles
import com.zimmy.sample.aisleapp.model.StatusModel
import com.zimmy.sample.aisleapp.model.TokenModel
import com.zimmy.sample.aisleapp.model.VerifyRequestBody
import com.zimmy.sample.aisleapp.network.AisleApiService
import javax.inject.Inject

class AisleRepository @Inject constructor(private val aisleApiService: AisleApiService) {

    suspend fun performPhoneLogin(number: String): StatusModel? {
        return aisleApiService.performPhoneLogin(number)
    }

    suspend fun verifyOtp(number: String, otp: String): TokenModel? {
        return aisleApiService.verifyOtp(verifyRequestBody = VerifyRequestBody(number, otp))
    }

    suspend fun getTestProfiles(authKey: String): Profiles? {
        return aisleApiService.getTestProfiles(authKey)
    }

}