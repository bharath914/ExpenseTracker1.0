package com.bharath.expensetracker.ui.theme.onboarding.di

import android.content.Context
import com.bharath.expensetracker.ui.theme.onboarding.data.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object OnBoardModule{
    @Provides
    @Singleton
    fun provideDataStoreRepo(@ApplicationContext context:Context)= DataStoreRepository(context)
}