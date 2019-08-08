package com.sphinx.dailystorykt.ui.home

import com.sphinx.dailystorykt.data.DataManager
import com.sphinx.dailystorykt.rxproviders.AppSchedulerProvider
import com.sphinx.dailystorykt.ui.base.BaseViewModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by sphinx on 05/08/2019
 */
class HomeViewModel @Inject constructor(
    private val view: HomeView,
    dataManager: DataManager,
    schedulerProvider: AppSchedulerProvider
) : BaseViewModel(dataManager = dataManager,schedulerProvider = schedulerProvider) {

//    private lateinit var trendingList : MutableList<NewsResponseModel.ArticleModel>
//    private lateinit var topList : MutableList<NewsResponseModel.ArticleModel>


    //i dont like livedate since it exposes states of the class / viewmodel by making
    //it public so activity can observe, but it wont crash as live data will not emit
    //to destroyed objects.
    private fun getNytTrendingnews() {
        compositeDisposable.add(dataManager.doNytRecentApiCall()
            .subscribeOn(schedulerProvider.io())
            .flatMap { response ->
                Observable
                    .fromIterable(response.articles)
                    .filter { article -> article.urlToImage.isNotEmpty() }
                    .toList()
            }
            .observeOn(schedulerProvider.ui())
            .subscribe({
                    list -> view.resetTrendingListAdapter(list) //now here we can use live data,
                //check if using live data we can show toast and other ui so no need to have a view interface
            },{
                //error
                view.notify("error")
            })
        )
    }

    fun getNYTRecentNews() {
        compositeDisposable.add(dataManager.doNytRecentApiCall()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                response -> view.resetTrendingListAdapter(response.articles)
            }, {
                view.notify("error")
            })
        )
    }

    fun getCNNTrendingNews() {
        compositeDisposable.add(dataManager.doCnnTrendingApiCall()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                    response -> view.resetTrendingListAdapter(response.articles)
            }, {
                view.notify("error")
            })
        )
    }

    fun getCNNRecentNews() {
        compositeDisposable.add(dataManager.doCnnRecentApiCall()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                    response -> view.resetTrendingListAdapter(response.articles)
            }, {
                view.notify("error")
            })
        )
    }

    fun getBBCTrendingNews() {
        compositeDisposable.add(dataManager.doBbcTrendingApiCall()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                    response -> view.resetTrendingListAdapter(response.articles)
            }, {
                view.notify("error")
            })
        )
    }

    fun getBBCRecentNews() {
        compositeDisposable.add(dataManager.doBbcRecentApiCall()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                    response -> view.resetTrendingListAdapter(response.articles)
            }, {
                view.notify("error")
            })
        )
    }
}