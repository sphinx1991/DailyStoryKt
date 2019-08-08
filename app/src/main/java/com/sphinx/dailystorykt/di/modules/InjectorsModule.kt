package com.sphinx.dailystorykt.di.modules

import com.sphinx.dailystorykt.ui.home.HomeActivity
import com.sphinx.dailystorykt.ui.home.HomeActivityModule
import com.sphinx.dailystorykt.ui.info.InfoActivity
import com.sphinx.dailystorykt.ui.info.InfoActivityModule
import com.sphinx.dailystorykt.ui.splash.SplashActivity
import com.sphinx.dailystorykt.ui.splash.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InjectorsModule {

    @ContributesAndroidInjector(modules = arrayOf(SplashActivityModule::class))
    abstract fun splashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = arrayOf(HomeActivityModule::class))
    abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = arrayOf(InfoActivityModule::class))
    abstract fun infoActivity(): InfoActivity

}