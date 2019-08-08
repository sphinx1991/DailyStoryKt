package com.sphinx.dailystorykt.ui.home

import android.os.Bundle
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

    private val topNewsListAdapter = TopNewsListAdapter()
    private val trendingNewsListAdapter = TrendingNewsListAdapter()

    @Inject lateinit var mHomeViewModel: HomeViewModel


    override fun resetTrendingListAdapter(articles: List<NewsResponseModel.ArticleModel>?) {
        topNewsListAdapter.apply {
            setList(articles)
            notifyDataSetChanged()
        }
    }

    override fun resetTopListAdapter(articles: List<NewsResponseModel.ArticleModel>?) {
        trendingNewsListAdapter.apply {
            setList(articles)
            notifyDataSetChanged()
        }
    }


    override fun openSomeActivity() {
        startActivity<InfoActivity>(
            "article" to "NewsResponseModel.ArticleModel" as Serializable
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

}