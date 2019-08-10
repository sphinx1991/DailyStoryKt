package com.sphinx.dailystorykt.ui.home

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sphinx.dailystorykt.BR
import com.sphinx.dailystorykt.R
import com.sphinx.dailystorykt.data.NewsResponseModel
import com.sphinx.dailystorykt.databinding.ActivityHomeBinding
import com.sphinx.dailystorykt.ui.base.BaseActivity
import com.sphinx.dailystorykt.ui.home.news.top.TopNewsListAdapter
import com.sphinx.dailystorykt.ui.home.news.trending.TrendingNewsListAdapter
import com.sphinx.dailystorykt.ui.info.InfoActivity
import kotlinx.android.synthetic.main.activity_home.*
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
        setupViews()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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
            getViewDataBinding().rvTrending.scrollToPosition(0)
        })
        mHomeViewModel.getTopList().observe(this, Observer { articles ->
            topNewsListAdapter.setList(articles)
            getViewDataBinding().rvToday.scrollToPosition(0)
        })
        mHomeViewModel.compositeDisposable.add(topNewsListAdapter.getOnClickSubject()
            .subscribe { data ->
                openInfoActivity(data)
            })
        mHomeViewModel.compositeDisposable.add(trendingNewsListAdapter.getOnClickSubject()
            .subscribe { data ->
                openInfoActivity(data)
            })

    }

    private fun setupViews() {
        val binding = getViewDataBinding()
        val actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.drawerLayout,
            R.string.Open, R.string.Close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.isDrawerIndicatorEnabled = true
        actionBarDrawerToggle.syncState()
        binding.nv.bringToFront()
        binding.nv.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.exit -> {
                    finish()
                    true
                }
                else -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
            }
        }
        //today trending news adapter
        binding.rvTrending.adapter = trendingNewsListAdapter
        binding.rvTrending.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false
        )

        //today top news adapter
        binding.rvToday.adapter = topNewsListAdapter
        binding.rvToday.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        mHomeViewModel.getNytTrendingNews()
                        mHomeViewModel.getNYTRecentNews()
                    }
                    1 -> {
                        mHomeViewModel.getCNNTrendingNews()
                        mHomeViewModel.getCNNRecentNews()
                    }
                    else -> {
                        mHomeViewModel.getBBCTrendingNews()
                        mHomeViewModel.getBBCRecentNews()
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
}