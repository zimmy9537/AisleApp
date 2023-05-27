package com.zimmy.sample.aisleapp.usecase

import com.zimmy.sample.aisleapp.model.Profiles
import com.zimmy.sample.aisleapp.model.StatusModel
import com.zimmy.sample.aisleapp.model.TokenModel
import com.zimmy.sample.aisleapp.network.ResultData
import com.zimmy.sample.aisleapp.repository.AisleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AisleUseCase @Inject constructor(
    private val aisleRepository: AisleRepository
) {
    suspend fun performPhoneLogin(number: String): Flow<ResultData<StatusModel>> {
        return flow {
            emit(ResultData.Loading)

            val statusModel = aisleRepository.performPhoneLogin(number)

            val resultData = if (statusModel == null) {
                ResultData.Failed()
            } else {
                ResultData.Success(statusModel)
            }

            emit(resultData)
        }.catch {
            emit(ResultData.Failed())
        }
    }

    suspend fun verifyOtp(number: String, otp: String): Flow<ResultData<TokenModel>> {
        return flow {
            emit(ResultData.Loading)

            val tokenModel = aisleRepository.verifyOtp(number, otp)

            val resultData = if (tokenModel == null) {
                ResultData.Failed()
            } else {
                ResultData.Success(tokenModel)
            }

            emit(resultData)
        }.catch {
            emit(ResultData.Failed())
        }
    }

    suspend fun getTestProfiles(authKey: String): Flow<ResultData<Profiles>> {
        return flow {
            emit(ResultData.Loading)

            val profiles = aisleRepository.getTestProfiles(authKey)

            val resultData = if (profiles == null) {
                ResultData.Failed()
            } else {
                ResultData.Success(profiles)
            }

            emit(resultData)
        }.catch {
            emit(ResultData.Failed())
        }
    }
}