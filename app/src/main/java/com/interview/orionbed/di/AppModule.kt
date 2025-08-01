package com.interview.orionbed.di

import com.interview.orionbed.repository.OrionBedRepository
import com.interview.orionbed.repository.OrionBedRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindOrionRepository(
        impl: OrionBedRepositoryImpl
    ): OrionBedRepository
}
