package com.sphinx.dailystorykt.di.modules

import android.content.Context
import com.sphinx.dailystorykt.App
import com.sphinx.dailystorykt.data.AppDataManager
import com.sphinx.dailystorykt.data.DataManager
import com.sphinx.dailystorykt.rxproviders.AppSchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: App): Context = application

    @Provides
    fun provideSchedulerProvider(): AppSchedulerProvider = AppSchedulerProvider()

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager = appDataManager
}