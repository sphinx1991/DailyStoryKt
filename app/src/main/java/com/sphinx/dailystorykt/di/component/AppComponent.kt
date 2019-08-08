package com.sphinx.dailystorykt.di.component

import com.sphinx.dailystorykt.App
import com.sphinx.dailystorykt.di.modules.AppModule
import com.sphinx.dailystorykt.di.modules.InjectorsModule
import com.sphinx.dailystorykt.di.modules.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AndroidSupportInjectionModule::class,
        InjectorsModule::class,
        AppModule::class,
        NetworkModule::class
    )
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}