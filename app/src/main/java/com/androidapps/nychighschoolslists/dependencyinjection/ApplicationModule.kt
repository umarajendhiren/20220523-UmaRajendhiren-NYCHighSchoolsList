package com.androidapps.nychighschoolslists.dependencyinjection

import android.content.Context
import com.androidapps.nychighschoolslists.retrofit.RetrofitService
import com.example.android.advancedcoroutines.utils.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/*Hilt dependency injection library use this class to get RetrofitService instance ,schoolDao instance  */
@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Singleton
    @Provides
    public fun provideRetrofitInstance(): RetrofitService {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://data.cityofnewyork.us/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        return retrofit.create(RetrofitService::class.java)
    }

    @Singleton
    @Provides
    fun schoolDao(@ApplicationContext context: Context) =
        AppDatabase.getInstance(context.applicationContext).SchoolsDao()

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {

        return context
    }
}

