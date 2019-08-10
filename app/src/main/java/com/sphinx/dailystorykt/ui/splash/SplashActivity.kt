package com.sphinx.dailystorykt.ui.splash

import android.os.Bundle
import com.sphinx.dailystorykt.BR
import com.sphinx.dailystorykt.R
import com.sphinx.dailystorykt.databinding.ActivitySplashBinding
import com.sphinx.dailystorykt.ui.base.BaseActivity
import com.sphinx.dailystorykt.ui.home.HomeActivity

import org.jetbrains.anko.*
import javax.inject.Inject

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashView {

    @Inject lateinit var mSplashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSplashViewModel.checkForNavigation()

    }

    override fun openHomeActivity() = startActivity<HomeActivity>()

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun getBindingVariable(): Int = BR.splashViewModel

    override fun getViewModel(): SplashViewModel = mSplashViewModel
}