package com.iwelogic.timer_presentation

sealed class TimerEvent {
    object OnForegroundStart : TimerEvent()
    object OnForegroundStop : TimerEvent()
    object OnClickStart : TimerEvent()
    object OnClickStop : TimerEvent()
}