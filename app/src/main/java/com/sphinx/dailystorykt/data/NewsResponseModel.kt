package com.sphinx.dailystorykt.data

import androidx.annotation.Keep
import java.io.Serializable


@Keep
data class NewsResponseModel(
    val status: String = "",
    val articles: List<ArticleModel> = emptyList()
): Serializable {

    @Keep
    data class ArticleModel(
        val author: String,
        val title: String = "",
        val description: String = "",
        val url: String = "",
        val publishedAt: String = "",
        val content: String = "",
        val urlToImage: String = ""
    ): Serializable
}
