package com.sphinx.dailystorykt.ui.info

import com.sphinx.dailystorykt.data.DataManager
import com.sphinx.dailystorykt.rxproviders.AppSchedulerProvider
import dagger.Module
import dagger.Provides

/**
 * Created by sphinx on 06/08/2019
 */
@Module
class InfoActivityModule {

    @Provides
    fun view(act: InfoActivity) : InfoView = act

    @Provides
    fun provideSplashViewModel(
        view: InfoActivity,
        dataManager: DataManager,
        schedulerProvider: AppSchedulerProvider
    ): InfoViewModel = InfoViewModel(view, dataManager, schedulerProvider)
}