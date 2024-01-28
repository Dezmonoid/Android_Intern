package com.example.android_intern.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.android_intern.R
import com.example.android_intern.data.AppDatabase
import com.example.android_intern.data.ForecastRepositoryImpl
import com.example.android_intern.data.WeatherApi
import com.example.android_intern.domain.ForecastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module : Application() {
    @Provides
    @Singleton
    @Named("BaseUrl")
    fun providesBaseUrl(): String {
        return "https://api.openweathermap.org/data/2.5/"
    }

    @Provides
    @Singleton
    fun providesWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        @Named("BaseUrl") baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room
            .databaseBuilder(
                context = context,
                klass = AppDatabase::class.java,
                name = R.string.app_data_base_name.toString()
            )
            .build()

    @Provides
    @Singleton
    fun providesRepository(
        appDatabase: AppDatabase,
        weatherApi: WeatherApi,
    ): ForecastRepository =
        ForecastRepositoryImpl(appDatabase, weatherApi)

    @Provides
    @Singleton
    fun providesOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}