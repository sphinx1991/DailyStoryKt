package com.sphinx.dailystorykt.ui.home.news.trending

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sphinx.dailystorykt.R
import com.sphinx.dailystorykt.RecyclerViewBindingAdapter
import com.sphinx.dailystorykt.data.NewsResponseModel
import com.sphinx.dailystorykt.databinding.LayoutTodayBinding
import com.sphinx.dailystorykt.ui.home.news.NewsViewModel
import io.reactivex.subjects.PublishSubject
import org.jetbrains.anko.*

/**
 * Created by sphinx on 05/08/2019
 */
class TrendingNewsListAdapter : RecyclerViewBindingAdapter<LayoutTodayBinding>() {

    private var trendingList : List<NewsResponseModel.ArticleModel> = emptyList()
    private val onClickSubject = PublishSubject.create<NewsResponseModel.ArticleModel>()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewPlaceHolder<LayoutTodayBinding> {
        val viewHolder = ViewPlaceHolder(onCreateViewBinding(parent, position))
        viewHolder.itemView.onClick {
            onClickSubject.onNext(trendingList[position])
        }
        return viewHolder
    }

    override fun onCreateViewBinding(parent: ViewGroup, viewType: Int): LayoutTodayBinding {
        return DataBindingUtil.inflate(
            parent.context.layoutInflater,
            R.layout.layout_trending,
            parent,
            false
        )
    }

    override fun onBindView(viewBinding: LayoutTodayBinding, position: Int) {
        viewBinding.apply {
            newsViewModel = NewsViewModel(trendingList[position])
        }
    }

    override fun getItemCount() = trendingList.size

    fun setList(list: List<NewsResponseModel.ArticleModel>?) {
        trendingList = list ?: emptyList()
    }

    fun getOnClickSubject(): PublishSubject<NewsResponseModel.ArticleModel> {
        return onClickSubject
    }

}