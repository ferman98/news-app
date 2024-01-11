package co.ferman.saltweather.util.network

import android.content.Context
import co.ferman.saltweather.util.data.api.NetworkServices
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {
    companion object {
        fun call(context: Context): NetworkServices {
            val okhttp = OkHttpClient
                .Builder()
                .addInterceptor(ChuckerInterceptor(context))

            return Retrofit
                .Builder()
                .client(okhttp.build())
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkServices::class.java)
        }
    }
}