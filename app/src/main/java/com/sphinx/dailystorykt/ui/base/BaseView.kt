package com.sphinx.dailystorykt.ui.base

import android.view.View
import androidx.lifecycle.Lifecycle

interface BaseView {

//	val lifecycleProvider: LifecycleProvider<ActivityEvent>

	fun finish()

	fun notify(msg: String)

	fun hideSoftKeyboard()

	fun getLifecycle(): Lifecycle

	fun showSoftKeyboard(view: View?)

	fun isNetworkPresent(): Boolean
}
