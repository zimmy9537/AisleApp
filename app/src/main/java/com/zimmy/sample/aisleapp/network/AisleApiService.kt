package com.zimmy.sample.aisleapp.network

import com.zimmy.sample.aisleapp.model.Profiles
import com.zimmy.sample.aisleapp.model.StatusModel
import com.zimmy.sample.aisleapp.model.TokenModel
import com.zimmy.sample.aisleapp.model.VerifyRequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AisleApiService {

    @POST(NetworkingConstants.PHONE_LOGIN)
    suspend fun performPhoneLogin(
        @Query("number") number: String,
    ): StatusModel?

    @POST(NetworkingConstants.VERIFY_OTP)
    suspend fun verifyOtp(
        @Body verifyRequestBody: VerifyRequestBody
    ): TokenModel?

    @GET(NetworkingConstants.TEST_PROFILE_LIST)
    suspend fun getTestProfiles(
        @Header("Authorization") authKey: String
    ): Profiles?
}