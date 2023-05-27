package com.zimmy.sample.aisleapp.di

import com.zimmy.sample.aisleapp.network.AisleApiService
import com.zimmy.sample.aisleapp.repository.AisleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataRepositoryModule {

    @Provides
    fun provideDataRepository(aisleApiService: AisleApiService): AisleRepository{
        return AisleRepository(aisleApiService)
    }
}