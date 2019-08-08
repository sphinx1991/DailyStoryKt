package com.sphinx.dailystorykt.ui.home.news.top

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sphinx.dailystorykt.R
import com.sphinx.dailystorykt.RecyclerViewBindingAdapter
import com.sphinx.dailystorykt.data.NewsResponseModel
import com.sphinx.dailystorykt.databinding.LayoutTodayBinding
import com.sphinx.dailystorykt.ui.home.news.NewsViewModel
import io.reactivex.subjects.PublishSubject
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.onClick

/**
 * Created by sphinx on 05/08/2019
 */
class TopNewsListAdapter : RecyclerViewBindingAdapter<LayoutTodayBinding>() {

    private var topNewsList: List<NewsResponseModel.ArticleModel> = emptyList()
    private val onClickSubject = PublishSubject.create<NewsResponseModel.ArticleModel>()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewPlaceHolder<LayoutTodayBinding> {
        val viewHolder = ViewPlaceHolder(onCreateViewBinding(parent, position))
        viewHolder.itemView.onClick {
            onClickSubject.onNext(topNewsList[position])
        }
        return viewHolder
    }

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

    fun getOnClickSubject(): PublishSubject<NewsResponseModel.ArticleModel> {
        return onClickSubject
    }

}