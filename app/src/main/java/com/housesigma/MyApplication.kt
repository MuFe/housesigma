package com.housesigma

import android.app.Application
import com.housesigma.di.commonModule
import com.housesigma.di.networkModule
import com.housesigma.di.viewModelModule
import com.housesigma.image.ImageLoader
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                viewModelModule,
                commonModule,
                networkModule,
            )
        }

//
//        Thread.setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler { thread, throwable ->
//            throw  throwable
//        })
        ImageLoader.getDefault().diskCacheOptions()
            .setDiskCacheDirPath(getExternalFilesDir("Cache")?.path ?: filesDir.path)
            .setDiskCacheFolderName("Image")
            .setDiskCacheSize(2 * 1024 * 1024) // 设置磁盘缓存2G
            .setBitmapPoolSize(2.0f)
            .setMemoryCacheSize(1.5f)
            .build()

    }
}
