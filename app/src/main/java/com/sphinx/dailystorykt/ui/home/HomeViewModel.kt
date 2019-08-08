package com.sphinx.dailystorykt.ui.home

import androidx.lifecycle.MutableLiveData
import com.sphinx.dailystorykt.data.DataManager
import com.sphinx.dailystorykt.data.NewsResponseModel
import com.sphinx.dailystorykt.rxproviders.AppSchedulerProvider
import com.sphinx.dailystorykt.ui.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by sphinx on 05/08/2019
 */
class HomeViewModel @Inject constructor(
    private val view: HomeView,
    dataManager: DataManager,
    schedulerProvider: AppSchedulerProvider
) : BaseViewModel(dataManager = dataManager, schedulerProvider = schedulerProvider) {

    private var trendingList = MutableLiveData<List<NewsResponseModel.ArticleModel>>()
    private var topList = MutableLiveData<List<NewsResponseModel.ArticleModel>>()

    private fun getNytTrendingnews() {
        compositeDisposable.add(dataManager.doNytTrendingApiCall()
            .subscribeOn(schedulerProvider.io())
            .flatMap { response ->
                Observable
                    .fromIterable(response.articles)
                    .filter { article -> article.urlToImage.isNotEmpty() }
                    .toList()
            }
            .observeOn(schedulerProvider.ui())
            .subscribe({ list ->
                trendingList.setValue(list)
            }, {
                //error
                view.notify("error")
            })
        )
    }

    fun getNYTRecentNews() {
        compositeDisposable.add(dataManager.doNytRecentApiCall()
            .subscribeOn(schedulerProvider.io())
            .flatMap { response ->
                Observable
                    .fromIterable(response.articles)
                    .filter { article -> article.urlToImage.isNotEmpty() }
                    .toList()
            }
            .observeOn(schedulerProvider.ui())
            .subscribe({ list ->
                topList.setValue(list)
            }, {
                //error
                view.notify("error")
            })
        )
    }

    fun getCNNTrendingNews() {
        compositeDisposable.add(dataManager.doCnnTrendingApiCall()
            .subscribeOn(schedulerProvider.io())
            .flatMap { response ->
                Observable
                    .fromIterable(response.articles)
                    .filter { article -> article.urlToImage.isNotEmpty() }
                    .toList()
            }
            .observeOn(schedulerProvider.ui())
            .subscribe({ list ->
                trendingList.setValue(list)
            }, {
                //error
                view.notify("error")
            })
        )
    }

    fun getCNNRecentNews() {
        compositeDisposable.add(dataManager.doCnnRecentApiCall()
            .subscribeOn(schedulerProvider.io())
            .flatMap { response ->
                Observable
                    .fromIterable(response.articles)
                    .filter { article -> article.urlToImage.isNotEmpty() }
                    .toList()
            }
            .observeOn(schedulerProvider.ui())
            .subscribe({ list ->
                topList.setValue(list)
            }, {
                //error
                view.notify("error")
            })
        )
    }

    fun getBBCTrendingNews() {
        compositeDisposable.add(dataManager.doBbcTrendingApiCall()
            .subscribeOn(schedulerProvider.io())
            .flatMap { response ->
                Observable
                    .fromIterable(response.articles)
                    .filter { article -> article.urlToImage.isNotEmpty() }
                    .toList()
            }
            .observeOn(schedulerProvider.ui())
            .subscribe({ list ->
                trendingList.setValue(list)
            }, {
                //error
                view.notify("error")
            })
        )
    }

    fun getBBCRecentNews() {
        compositeDisposable.add(dataManager.doBbcRecentApiCall()
            .subscribeOn(schedulerProvider.io())
            .flatMap { response ->
                Observable
                    .fromIterable(response.articles)
                    .filter { article -> article.urlToImage.isNotEmpty() }
                    .toList()
            }
            .observeOn(schedulerProvider.ui())
            .subscribe({ list ->
                topList.setValue(list)
            }, {
                //error
                view.notify("error")
            })
        )
    }

    fun getTrendingList(): MutableLiveData<List<NewsResponseModel.ArticleModel>> = trendingList

    fun getTopList(): MutableLiveData<List<NewsResponseModel.ArticleModel>> = topList

}