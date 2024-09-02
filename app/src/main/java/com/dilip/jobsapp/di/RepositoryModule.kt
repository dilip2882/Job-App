package com.dilip.jobsapp.di

import com.dilip.jobsapp.data.repository.JobsRepositoryImpl
import com.dilip.jobsapp.data.web.JobsApi
import com.dilip.jobsapp.domain.repository.JobsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesJobsRepository(api: JobsApi): JobsRepository {
        return JobsRepositoryImpl(api)
    }
}