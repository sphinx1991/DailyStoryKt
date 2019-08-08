package com.sphinx.dailystorykt.ui.base

import androidx.lifecycle.ViewModel
import com.sphinx.dailystorykt.data.DataManager
import com.sphinx.dailystorykt.rxproviders.AppSchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(
    val dataManager: DataManager,
    val schedulerProvider: AppSchedulerProvider
) {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
}