package com.dilip.jobsapp.di

import android.content.Context
import com.dilip.jobsapp.database.JobsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): JobsDatabase {
        return JobsDatabase.getDatabase(context)
    }
}