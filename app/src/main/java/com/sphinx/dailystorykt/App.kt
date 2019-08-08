package com.sphinx.dailystorykt

import androidx.appcompat.app.AppCompatDelegate
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class App : DaggerApplication() {

	override fun applicationInjector(): AndroidInjector<App> = DaggerAppComponent
			.builder()
			.create(this@App)

	override fun onCreate() {
		super.onCreate()

		// Required for Kitkat and below to load Vector Drawables
		AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

		@Suppress("ConstantConditionIf")
		if (BuildConfig.BUILD_TYPE != "release") {
			Stetho.initializeWithDefaults(applicationContext)
		}
	}

}
