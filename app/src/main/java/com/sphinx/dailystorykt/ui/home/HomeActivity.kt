package com.sphinx.dailystorykt.ui.home

import android.os.Bundle
import androidx.lifecycle.Observer
import com.sphinx.dailystorykt.BR
import com.sphinx.dailystorykt.R
import com.sphinx.dailystorykt.data.NewsResponseModel
import com.sphinx.dailystorykt.databinding.ActivityHomeBinding
import com.sphinx.dailystorykt.ui.base.BaseActivity
import com.sphinx.dailystorykt.ui.home.news.top.TopNewsListAdapter
import com.sphinx.dailystorykt.ui.home.news.trending.TrendingNewsListAdapter
import com.sphinx.dailystorykt.ui.info.InfoActivity
import org.jetbrains.anko.startActivity
import java.io.Serializable
import javax.inject.Inject

/**
 * Created by sphinx on 05/08/2019
 */
class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeView {

    @Inject lateinit var mHomeViewModel: HomeViewModel

    private val topNewsListAdapter = TopNewsListAdapter()
    private val trendingNewsListAdapter = TrendingNewsListAdapter()

    override fun openInfoActivity(articleModel: NewsResponseModel.ArticleModel) {
        startActivity<InfoActivity>(
            "article" to articleModel as Serializable
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeToLiveData()
        mHomeViewModel.compositeDisposable.add(topNewsListAdapter.getOnClickSubject()
            .subscribe { data ->
                openInfoActivity(data)
            })
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun getBindingVariable(): Int {
        return BR.homeViewModel
    }

    override fun getViewModel(): HomeViewModel {
        return mHomeViewModel
    }

    private fun subscribeToLiveData() {
        mHomeViewModel.getTrendingList().observe(this, Observer { articles ->
            trendingNewsListAdapter.setList(articles)
//            getViewDataBinding().rvTrending.scrollToPosition(0)
        })
        mHomeViewModel.getTopList().observe(this, Observer { articles ->
            topNewsListAdapter.setList(articles)
//            getViewDataBinding().rvToday.scrollToPosition(0)
        })

    }

}