package com.diatomicsoft.kotlinhelper.model.networking.healper

import androidx.viewbinding.BuildConfig
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetroFitInstance {

    companion object {

        private var client: Retrofit? = null
        private lateinit var okHttpClient: OkHttpClient.Builder

        @Synchronized
        fun instance(): Retrofit {
            if (client == null) {
                okHttpClient = OkHttpClient.Builder()
                    .readTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES).addInterceptor { chain: Interceptor.Chain ->
                        val original = chain.request()
                        val requestBuilder: Request.Builder =
                            original
                                .newBuilder()
                                .method(original.method(), original.body())
                        val request = requestBuilder.build()
                        chain.proceed(request)
                    }

                if (BuildConfig.DEBUG) {
                    val httpLoggingInterceptor = HttpLoggingInterceptor()
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    okHttpClient.addInterceptor(httpLoggingInterceptor)
                }
                client = Retrofit.Builder()
                    .baseUrl("https://www.apiName.com")
                    .client(okHttpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
            }
            return client!!
        }


    }

}