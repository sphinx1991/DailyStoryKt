package com.sphinx.dailystorykt.ui.splash

import com.sphinx.dailystorykt.data.DataManager
import com.sphinx.dailystorykt.rxproviders.AppSchedulerProvider
import com.sphinx.dailystorykt.ui.base.BaseViewModel
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val view: SplashView,
    dataManager: DataManager,
    schedulerProvider: AppSchedulerProvider
) : BaseViewModel(dataManager, schedulerProvider) {

    private fun decideNextActivity() {
        view.openHomeActivity()
    }

    fun checkForNavigation() {
        compositeDisposable.add(
            Observable.timer(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    decideNextActivity()
                }, {
                    view.notify("error")
                })
        )
    }
}