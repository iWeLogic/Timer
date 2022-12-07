package com.iwelogic.timer_domain.use_case

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TimerUseCaseImp : TimerUseCase {

    override fun getTimer(currentTimeInSeconds: Long): Flow<Long> {

        return flow {
            emit(currentTimeInSeconds)
            var temp = currentTimeInSeconds
            while (true) {
                delay(1000)
                emit(++temp)
            }
        }.flowOn(Dispatchers.Default)
    }
}

