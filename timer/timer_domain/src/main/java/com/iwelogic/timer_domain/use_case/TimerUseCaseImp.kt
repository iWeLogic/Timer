package com.iwelogic.timer_domain.use_case

import com.iwelogic.timer_domain.repository.TimerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TimerUseCaseImp(private val timerRepository: TimerRepository) : TimerUseCase {

    override fun getTimer(): Flow<Long> {
        var currentTimeInSeconds = timerRepository.getTimerSeconds()
        return flow {
            emit(currentTimeInSeconds)
            while (true) {
                delay(1000)
                emit(++currentTimeInSeconds)
                timerRepository.storeTimerSeconds(currentTimeInSeconds)
            }
        }.flowOn(Dispatchers.Default)
    }

    override fun clearSavedTime() {
        timerRepository.storeTimerSeconds(0L)
    }

    override fun isTimerActive() = timerRepository.getTimerSeconds() > 0L
}

