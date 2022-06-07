package com.example.weatherapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.example.weatherapp.data.local.database.WeatherDao
import com.example.weatherapp.data.local.database.WeatherDatabase
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.data.remote.WeatherApiService
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Singleton
    @Provides
    fun provideWeatherApi(): WeatherApiService =
        Retrofit.Builder().baseUrl(WeatherApi.BaseURL)
            .addConverterFactory(GsonConverterFactory.create()).build().create()


    @Singleton
    @Provides
    fun provideWeatherDatabase(
        @ApplicationContext context: Context,

        ): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            WeatherDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }


    //no need to add singleton cause dao is singleton by RoomDatabase
    @Provides
    fun providerWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao {
        return weatherDatabase.userDao()
    }


    @Singleton
    @Provides
    @ApplicationCoroutineScope
    fun provideCoroutineScope() = CoroutineScope(SupervisorJob())

    @Singleton
    @Provides
    fun providerWeatherRepository(
        weatherApiService: WeatherApiService,
        weatherDao: WeatherDao,
       @ApplicationCoroutineScope  scope: CoroutineScope
    ): WeatherRepository = WeatherRepositoryImpl(weatherApiService, weatherDao,scope)



    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }


}

@Qualifier
annotation class ApplicationCoroutineScope