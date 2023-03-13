package com.example.moviesapp.di

import com.example.moviesapp.data.api.*
import com.example.moviesapp.data.other.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): API {
        return API(
            getNowPlayingAPI = retrofit.create(NowPlayingAPI::class.java),
            getUpcomingAPI = retrofit.create(UpcomingAPI::class.java),
            getTopRatedAPI = retrofit.create(TopRatedAPI::class.java),
            getPopularAPI = retrofit.create(PopularAPI::class.java),
            getTrailerAPI = retrofit.create(MovieTrailerAPI::class.java),
            getReviewsAPI = retrofit.create(MovieReviewsAPI::class.java),
            getCreditsAPI = retrofit.create(MovieCreditsAPI::class.java),
            getMovieDetailsAPI = retrofit.create(MovieDetailsAPI::class.java),
            getPersonDetailsAPI = retrofit.create(PersonDetailsAPI::class.java),
        )
    }
}
