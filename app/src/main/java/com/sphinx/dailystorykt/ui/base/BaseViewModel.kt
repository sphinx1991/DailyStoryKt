package com.sphinx.dailystorykt.ui.base

import androidx.lifecycle.ViewModel
import com.sphinx.dailystorykt.data.DataManager
import com.sphinx.dailystorykt.rxproviders.AppSchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(
    val dataManager: DataManager,
    val schedulerProvider: AppSchedulerProvider
) : ViewModel() {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}