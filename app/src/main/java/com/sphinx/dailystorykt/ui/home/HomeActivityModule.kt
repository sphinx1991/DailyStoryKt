package com.sphinx.dailystorykt.ui.home

import com.sphinx.dailystorykt.data.DataManager
import com.sphinx.dailystorykt.rxproviders.AppSchedulerProvider
import dagger.Module
import dagger.Provides

/**
 * Created by sphinx on 06/08/2019
 */
@Module
class HomeActivityModule {

    @Provides
    fun view(act: HomeActivity) : HomeView = act

    @Provides
    fun providesHomeViewModel(view: HomeActivity, dataManager: DataManager,
                              appSchedulerProvider: AppSchedulerProvider
    ) = HomeViewModel(view, dataManager, appSchedulerProvider)
}