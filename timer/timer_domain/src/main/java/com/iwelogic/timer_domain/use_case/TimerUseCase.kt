package com.iwelogic.timer_domain.use_case

import kotlinx.coroutines.flow.Flow

interface TimerUseCase {
    fun getTimer(currentTimeInSeconds: Long): Flow<Long>
}

