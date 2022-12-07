package com.iwelogic.timer_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iwelogic.timer_domain.use_case.TimerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(private val timerUseCase: TimerUseCase) : ViewModel() {

    private var timerJob: Job? = null

    var uiState by mutableStateOf(TimerUiState(0, false))
        private set

    fun onEvent(event: TimerEvent) {
        when (event) {
            is TimerEvent.OnClickStart -> {
                uiState = uiState.copy(enableStatus = true)
                runTimer()
            }
            is TimerEvent.OnClickStop -> {
                uiState = uiState.copy(
                    timerSeconds = 0,
                    enableStatus = false
                )
                timerJob?.cancel()
            }
            is TimerEvent.OnForegroundStop -> {
                timerJob?.cancel()
            }
            is TimerEvent.OnForegroundStart -> {
                if (uiState.enableStatus)
                    runTimer()
            }
        }
    }

    private fun runTimer() {
        timerJob = viewModelScope.launch {
            timerUseCase.getTimer(uiState.timerSeconds).collect {
                uiState = uiState.copy(timerSeconds = it)
            }
        }
    }
}