package com.iwelogic.timer_presentation.di

import com.iwelogic.timer_domain.repository.TimerRepository
import com.iwelogic.timer_domain.use_case.TimerUseCase
import com.iwelogic.timer_domain.use_case.TimerUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object TimerUseCaseModule {

    @Provides
    fun provide(timerRepository: TimerRepository): TimerUseCase {
        return TimerUseCaseImp(timerRepository)
    }
}