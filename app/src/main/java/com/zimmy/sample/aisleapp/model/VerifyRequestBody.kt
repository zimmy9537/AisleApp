package com.zimmy.sample.aisleapp.model

data class VerifyRequestBody(
    val number: String,
    val otp: String
)