package com.housesigma.di



import com.housesigma.network.ApiService
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder
import com.housesigma.BuildConfig
import com.housesigma.network.IntTypeAdapter



val networkModule = module {
    val BASE_URL=""
    factory {
        get<Retrofit>{ parametersOf(get<String>(named(BASE_URL)))}.create(ApiService::class.java)
    }
    var gson= GsonBuilder()
        .registerTypeAdapter(Int::class.javaPrimitiveType, IntTypeAdapter())
        .registerTypeAdapter(Int::class.java, IntTypeAdapter()).create()
    var fac=GsonConverterFactory.create(gson)
    factory { (baseUrl:String)->
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(get())
            .addConverterFactory(fac)
            .build()
    }

    factory {
        val builder = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(20, TimeUnit.SECONDS)
            .addInterceptor {
                val request = it.request()
                val headers: Headers = Headers.Builder().addAll(request.headers()).build()

                it.let { chain ->
                    chain.withConnectTimeout(60, TimeUnit.SECONDS)
                        .withReadTimeout(60, TimeUnit.SECONDS)
                        .withWriteTimeout(60, TimeUnit.SECONDS)
                }.proceed(
                    request
                        .newBuilder()
                        .headers(headers)
                        .build()
                )
            }
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logging)
        }
        builder.build()
    }

    factory {
        var gson= GsonBuilder()
            .registerTypeAdapter(Int::class.javaPrimitiveType, IntTypeAdapter())
            .registerTypeAdapter(Int::class.java, IntTypeAdapter()).create()
        GsonConverterFactory.create(gson)
    }

    factory(named(BASE_URL)) {
        "http://113.31.157.71:8000"
    }

}