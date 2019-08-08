package com.sphinx.dailystorykt.data.remote

import com.sphinx.dailystorykt.data.NewsResponseModel
import io.reactivex.Single
import retrofit2.http.GET

interface ApiHelper {

//    @GET(ApiEndPoint.ENDPOINT_TRENDING_NYT)
    fun doNytTrendingApiCall(): Single<NewsResponseModel>

//    @GET(ApiEndPoint.ENDPOINT_RECENT_NYT)
    fun doNytRecentApiCall(): Single<NewsResponseModel>

//    @GET(ApiEndPoint.ENDPOINT_TRENDING_CNN)
    fun doCnnTrendingApiCall(): Single<NewsResponseModel>

//    @GET(ApiEndPoint.ENDPOINT_RECENT_CNN)
    fun doCnnRecentApiCall(): Single<NewsResponseModel>

//    @GET(ApiEndPoint.ENDPOINT_TRENDING_BBC)
    fun doBbcTrendingApiCall(): Single<NewsResponseModel>

//    @GET(ApiEndPoint.ENDPOINT_RECENT_BBC)
    fun doBbcRecentApiCall(): Single<NewsResponseModel>

}