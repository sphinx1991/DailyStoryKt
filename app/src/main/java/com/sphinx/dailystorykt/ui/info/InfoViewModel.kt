package com.sphinx.dailystorykt.ui.info

import androidx.databinding.ObservableField
import com.google.gson.JsonParser
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
 * Created by sphinx on 06/08/2019
 */
class InfoViewModel @Inject constructor(
    private val view: InfoView,
    dataManager: DataManager,
    schedulerProvider: AppSchedulerProvider
) : BaseViewModel(dataManager, schedulerProvider) {

    companion object {
        private val isoDateFormatOne = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        private val isoDateFormatTwo = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        private val readableDateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.US)
    }

    var heading: ObservableField<String> = ObservableField()
    var date: ObservableField<String> = ObservableField()
    var imgUrl: ObservableField<String> = ObservableField()
    var description: ObservableField<String> = ObservableField()

    fun setData(data: NewsResponseModel.ArticleModel) {

        heading.set(data.title)
        date.set(data.publishedAt)
        imgUrl.set(data.urlToImage)
        description.set(data.description)

        isoDateFormatOne.timeZone = TimeZone.getTimeZone("GMT")
        isoDateFormatTwo.timeZone = TimeZone.getTimeZone("GMT")
        readableDateFormat.timeZone = Calendar.getInstance().timeZone
        try {
            this.date.set(readableDateFormat.format(isoDateFormatOne.parse(date.get())))
        } catch (e: ParseException) {
            try {
                this.date.set(readableDateFormat.format(isoDateFormatTwo.parse(date.get())))
            } catch (e1: ParseException) {
                this.date.set("Unknown Date")
            }

        }
    }
}