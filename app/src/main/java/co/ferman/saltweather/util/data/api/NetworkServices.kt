package co.ferman.saltweather.util.data.api

import co.ferman.saltweather.util.Constants
import co.ferman.saltweather.util.data.model.NewsTopHeadlines
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface NetworkServices {

    @Headers("X-Api-Key:${Constants.API_KEY}")
    @GET("top-headlines?domains=techcrunch.com,thenextweb.com&country=us")
    suspend fun getNewsTopHeadlines(): Response<NewsTopHeadlines>

    @Headers("X-Api-Key:${Constants.API_KEY}")
    @GET("everything?domains=techcrunch.com,thenextweb.com&sortBy=popularity&pageSize=30")
    suspend fun getNews(): Response<NewsTopHeadlines>
}