package com.sphinx.dailystorykt.data

import com.sphinx.dailystorykt.data.remote.ApiHelper
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(
    private val apiHelper: ApiHelper
) : DataManager {

    override fun doNytTrendingApiCall(): Single<NewsResponseModel> {
        return apiHelper.doNytTrendingApiCall()
    }

    override fun doNytRecentApiCall(): Single<NewsResponseModel> {
        return apiHelper.doNytRecentApiCall()
    }

    override fun doCnnTrendingApiCall(): Single<NewsResponseModel> {
        return apiHelper.doCnnTrendingApiCall()
    }

    override fun doCnnRecentApiCall(): Single<NewsResponseModel> {
        return apiHelper.doCnnRecentApiCall()
    }

    override fun doBbcTrendingApiCall(): Single<NewsResponseModel> {
        return apiHelper.doBbcTrendingApiCall()
    }

    override fun doBbcRecentApiCall(): Single<NewsResponseModel> {
        return apiHelper.doBbcRecentApiCall()
    }
}