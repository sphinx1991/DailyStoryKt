package com.sphinx.dailystorykt.ui.home.news.trending

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sphinx.dailystorykt.R
import com.sphinx.dailystorykt.RecyclerViewBindingAdapter
import com.sphinx.dailystorykt.data.DataManager
import com.sphinx.dailystorykt.data.NewsResponseModel
import com.sphinx.dailystorykt.databinding.LayoutTodayBinding
import com.sphinx.dailystorykt.rxproviders.AppSchedulerProvider
import com.sphinx.dailystorykt.ui.home.news.NewsViewModel
import org.jetbrains.anko.*

/**
 * Created by sphinx on 05/08/2019
 */
class TrendingNewsListAdapter : RecyclerViewBindingAdapter<LayoutTodayBinding>() {

    private var topNewsList : List<NewsResponseModel.ArticleModel> = emptyList()
//    private val dataMapper : TopNewsDataMapper()

    override fun onCreateViewBinding(parent: ViewGroup, viewType: Int): LayoutTodayBinding {
        return DataBindingUtil.inflate(
            parent.context.layoutInflater,
            R.layout.layout_today,
            parent,
            false
        )
    }

    override fun onBindView(viewBinding: LayoutTodayBinding, position: Int) {
        viewBinding.apply {
            newsViewModel = NewsViewModel(topNewsList[position])
        }
    }

    override fun getItemCount() = topNewsList.size

    fun setList(list: List<NewsResponseModel.ArticleModel>?) {
        topNewsList = list ?: emptyList()
    }

}