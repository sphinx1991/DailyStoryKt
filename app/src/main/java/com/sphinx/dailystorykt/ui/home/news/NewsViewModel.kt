package com.sphinx.dailystorykt.ui.home.news

import androidx.databinding.ObservableField
import com.sphinx.dailystorykt.data.DataManager
import com.sphinx.dailystorykt.data.NewsResponseModel
import com.sphinx.dailystorykt.rxproviders.AppSchedulerProvider
import com.sphinx.dailystorykt.ui.base.BaseViewModel
import io.reactivex.Observable
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by sphinx on 05/08/2019
 */
class NewsViewModel(articleModel: NewsResponseModel.ArticleModel) {

    companion object {
        private val isoDateFormatOne = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        private val isoDateFormatTwo = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        private val readableDateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.US)
    }

    var heading : ObservableField<String> = ObservableField(articleModel.title)
    var date: ObservableField<String> = ObservableField()
    var imgUrl: ObservableField<String> = ObservableField(articleModel.urlToImage)

    init {
       setDate(articleModel.publishedAt)
    }

    private fun setDate(date: String) {
        isoDateFormatOne.timeZone = TimeZone.getTimeZone("GMT")
        isoDateFormatTwo.timeZone = TimeZone.getTimeZone("GMT")
        readableDateFormat.timeZone = Calendar.getInstance().timeZone
        try {
            this.date.set(readableDateFormat.format(isoDateFormatOne.parse(date)))
        } catch (e: ParseException) {
            try {
                this.date.set(readableDateFormat.format(isoDateFormatTwo.parse(date)))
            } catch (e1: ParseException) {
                this.date.set("Unknown Date")
            }
        }
    }
}