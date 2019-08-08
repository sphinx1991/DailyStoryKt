package com.sphinx.dailystorykt.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.sphinx.dailystorykt.hideSoftKeyboard
import com.sphinx.dailystorykt.isNetworkConnected
import com.sphinx.dailystorykt.showSoftKeyBoard
import dagger.android.AndroidInjection
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.act
import org.jetbrains.anko.toast

/**
 * All activities inherit from this activity.
 * Contains utility functions.
 */
abstract class BaseActivity<T: ViewDataBinding, V: BaseViewModel> : AppCompatActivity(), BaseView, AnkoLogger {

    private lateinit var mViewDataBinding: T
    private var mViewModel: V ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        // Required for Kitkat and below to load Vector Drawables
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        performDataBinding()

    }

    override fun notify(msg: String) {
        toast(msg)
    }

    override fun hideSoftKeyboard() {
        act.hideSoftKeyboard()
    }

    override fun showSoftKeyboard(view: View?) {
        act.showSoftKeyBoard(view)
    }

    override fun isNetworkPresent() {
        application.isNetworkConnected()
    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()
    }

    //get binding
    fun getViewDataBinding(): T {
        return mViewDataBinding
    }

    abstract fun getLayoutId(): Int

    //get variable from each class
    abstract fun getBindingVariable(): Int

    //get viewmodel of each class
    abstract fun getViewModel(): V
}