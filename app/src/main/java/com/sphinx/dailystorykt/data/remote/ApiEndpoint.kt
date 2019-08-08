package com.sphinx.dailystorykt.data.remote

import com.sphinx.dailystorykt.BuildConfig

class ApiEndPoint {
    companion object {
        val ENDPOINT_TRENDING_NYT = "top-headlines?sources=the-new-york-times&apiKey=" + BuildConfig.API_KEY

        val ENDPOINT_RECENT_NYT = "everything?sources=the-new-york-times&apiKey=" + BuildConfig.API_KEY

        val ENDPOINT_TRENDING_CNN = "top-headlines?sources=cnn&apiKey=" + BuildConfig.API_KEY

        val ENDPOINT_RECENT_CNN = "everything?sources=cnn&apiKey=" + BuildConfig.API_KEY

        val ENDPOINT_TRENDING_BBC = "top-headlines?sources=bbc-news&apiKey=" + BuildConfig.API_KEY

        val ENDPOINT_RECENT_BBC = "everything?sources=bbc-news&apiKey=" + BuildConfig.API_KEY
    }
}