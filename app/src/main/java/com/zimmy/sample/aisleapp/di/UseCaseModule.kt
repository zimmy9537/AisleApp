package com.zimmy.sample.aisleapp.di

import com.zimmy.sample.aisleapp.repository.AisleRepository
import com.zimmy.sample.aisleapp.usecase.AisleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object UseCaseModule {

    @Provides
    fun providesDataUseCase(beerRepository: AisleRepository): AisleUseCase {
        return AisleUseCase(beerRepository)
    }
}