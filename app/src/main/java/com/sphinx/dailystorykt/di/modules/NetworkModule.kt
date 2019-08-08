package com.sphinx.dailystorykt.di.modules

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.sphinx.dailystorykt.BuildConfig
import com.sphinx.dailystorykt.data.remote.ApiHelper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        //constants
        const val url = "url"
    }

    @Provides
    @Singleton
    fun getNewsApi(
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): ApiHelper = Retrofit.Builder()
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
            .create(ApiHelper::class.java!!)

//    @Provides
//    @Singleton
//    fun gsonConverterFactory(): GsonConverterFactory =
//        GsonConverterFactory.create(
//            GsonBuilder().
//                registerTypeAdapterFactory(AutoValueGsonFactory.create())
//                .create()
//        )

    @Provides
    @Singleton
    fun rxJava2CallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun okHttpClient(stethoInterceptor: StethoInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(stethoInterceptor)
            .build()

    @Provides
    @Singleton
    fun stethoInterceptor(): StethoInterceptor = StethoInterceptor()

}