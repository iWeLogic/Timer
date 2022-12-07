package com.iwelogic.timer_data.repository

import android.content.Context
import com.iwelogic.timer_domain.repository.TimerRepository
import java.lang.ref.WeakReference

const val LOCAL_STORAGE = "LOCAL_STORAGE"
const val TIMER = "TIMER"

class TimerRepositoryImp(applicationContext: Context) : TimerRepository {

    var context: WeakReference<Context> = WeakReference(applicationContext)

    override fun storeTimerSeconds(value: Long) {
        context.get()?.getSharedPreferences(LOCAL_STORAGE, Context.MODE_PRIVATE)?.edit()?.putLong(TIMER, value)?.apply()
    }

    override fun getTimerSeconds(): Long {
        return context.get()?.getSharedPreferences(LOCAL_STORAGE, Context.MODE_PRIVATE)?.getLong(TIMER, 0L) ?: 0L
    }
}