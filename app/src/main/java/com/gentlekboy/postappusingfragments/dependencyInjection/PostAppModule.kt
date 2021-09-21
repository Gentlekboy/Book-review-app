package com.gentlekboy.postappusingfragments.dependencyInjection

import android.app.Application
import com.gentlekboy.postappusingfragments.database.AppDao
import com.gentlekboy.postappusingfragments.database.AppDatabase
import com.gentlekboy.postappusingfragments.network.ApiInterface
import com.gentlekboy.postappusingfragments.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PostAppModule {
    @Singleton
    @Provides
    fun connectedInterface(): ApiInterface{
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(loggingInterceptor).build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun getPostDatabase(context: Application): AppDatabase{
        return AppDatabase.getDbInstance(context)
    }

    @Singleton
    @Provides
    fun getPostDao(appDatabase: AppDatabase): AppDao {
        return appDatabase.getPostDao()
    }
}