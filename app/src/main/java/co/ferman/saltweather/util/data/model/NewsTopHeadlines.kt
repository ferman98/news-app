package co.ferman.saltweather.util.data.model

import com.google.gson.annotations.SerializedName

data class NewsTopHeadlines(
    @field:SerializedName("totalResults")
    val totalResults: Int = 0,

    @field:SerializedName("articles")
    val articles: List<ArticlesItem> = listOf(),

    @field:SerializedName("status")
    val status: String = ""
)

data class Source(

    @field:SerializedName("name")
    val name: String = "",

    @field:SerializedName("id")
    val id: String = ""
)

data class ArticlesItem(

    @field:SerializedName("publishedAt")
    val publishedAt: String = "",

    @field:SerializedName("author")
    val author: String = "",

    @field:SerializedName("urlToImage")
    val urlToImage: String? = null,

    @field:SerializedName("description")
    val description: String = "",

    @field:SerializedName("source")
    val source: Source = Source(),

    @field:SerializedName("title")
    val title: String = "",

    @field:SerializedName("url")
    val url: String = "",

    @field:SerializedName("content")
    val content: String = ""
)
