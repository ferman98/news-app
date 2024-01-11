package co.ferman.saltweather.util.data.di

import android.content.Context
import co.ferman.saltweather.util.data.api.NetworkServices
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NewsModule {

    @Provides
    fun provideNetworkServices(@ApplicationContext context: Context): NetworkServices {
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