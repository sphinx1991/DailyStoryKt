package com.sphinx.dailystorykt.rxproviders

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedulerProvider {

    fun ui(): Scheduler = AndroidSchedulers.mainThread()

    fun computation(): Scheduler = Schedulers.computation()

    fun io(): Scheduler = Schedulers.io()
}