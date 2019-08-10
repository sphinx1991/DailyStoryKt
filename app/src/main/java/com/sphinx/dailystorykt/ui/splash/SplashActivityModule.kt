package com.sphinx.dailystorykt.ui.splash

import com.sphinx.dailystorykt.data.DataManager
import com.sphinx.dailystorykt.rxproviders.AppSchedulerProvider
import dagger.Module
import dagger.Provides

/**
 * Created by sphinx on 09/08/2019
 */
@Module
class SplashActivityModule {

    @Provides
    fun view(act: SplashActivity): SplashView = act

    @Provides
    fun provideSplashViewModel(
        view: SplashView,
        dataManager: DataManager,
        schedulerProvider: AppSchedulerProvider
    ): SplashViewModel = SplashViewModel(view, dataManager, schedulerProvider)
}