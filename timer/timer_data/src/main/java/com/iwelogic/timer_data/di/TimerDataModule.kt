package com.iwelogic.timer_data.di

import android.content.Context
import com.iwelogic.timer_data.repository.TimerRepositoryImp
import com.iwelogic.timer_domain.repository.TimerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object TimerDataModule {

    @Provides
    @Singleton
    fun provideCoinsRepository(@ApplicationContext applicationContext: Context): TimerRepository {
        return TimerRepositoryImp(applicationContext)
    }
}
