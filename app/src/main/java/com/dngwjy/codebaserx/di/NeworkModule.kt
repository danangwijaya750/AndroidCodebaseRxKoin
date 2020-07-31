package com.dngwjy.codebaserx.di

import com.dngwjy.codebaserx.data.service.ApiService
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module{
    single{
        val timeOut = 60L
        return@single OkHttpClient.Builder()
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .writeTimeout(timeOut, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor { chain ->
                val req = chain.request()
                    .newBuilder()
                    .build()
                return@addInterceptor chain.proceed(req)
            }
            .build()
    }
    single{
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
    }
    single {
        createService<ApiService>(get())
    }
}
inline fun <reified T>createService(retrofit: Retrofit):T=retrofit.create(T::class.java)