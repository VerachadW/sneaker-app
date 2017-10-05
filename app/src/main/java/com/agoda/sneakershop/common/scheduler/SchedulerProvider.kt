package com.agoda.sneakershop.common.scheduler

import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

interface SchedulerProvider {
    fun main(): Scheduler
    fun io(): Scheduler
}

class SchedulerProviderImpl : SchedulerProvider {

    override fun main(): Scheduler = AndroidSchedulers.mainThread()

    override fun io(): Scheduler = Schedulers.io()

}