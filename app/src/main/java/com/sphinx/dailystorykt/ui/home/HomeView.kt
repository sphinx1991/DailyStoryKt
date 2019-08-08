package com.sphinx.dailystorykt.ui.home

import com.sphinx.dailystorykt.data.NewsResponseModel
import com.sphinx.dailystorykt.ui.base.BaseView

/**
 * Created by sphinx on 05/08/2019
 */
interface HomeView : BaseView {

    fun openInfoActivity(articleModel: NewsResponseModel.ArticleModel)
}