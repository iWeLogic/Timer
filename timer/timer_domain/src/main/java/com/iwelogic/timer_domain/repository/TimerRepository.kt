package com.iwelogic.timer_domain.repository

interface TimerRepository {
    fun storeTimerSeconds(value: Long)
    fun getTimerSeconds() : Long
}