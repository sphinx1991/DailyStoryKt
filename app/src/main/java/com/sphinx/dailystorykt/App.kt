package com.sphinx.dailystorykt

import androidx.appcompat.app.AppCompatDelegate
import com.facebook.stetho.Stetho
import com.sphinx.dailystorykt.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class App : DaggerApplication() {

	override fun applicationInjector(): AndroidInjector<App> {
		return DaggerAppComponent
			.builder()
			.create(this@App)
	}

	override fun onCreate() {
		super.onCreate()

		// Required for Kitkat and below to load Vector Drawables
		AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

		Stetho.initializeWithDefaults(applicationContext)
	}

}
