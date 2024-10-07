package com.example.eduardo.tecsa

import android.app.Application
import android.content.Context
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import com.example.eduardo.tecsa.lib.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application(), ImageLoaderFactory {
    init {
        instance = this
    }

    companion object {
        private var instance: App? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .memoryCachePolicy(CachePolicy.ENABLED).memoryCache {
                MemoryCache.Builder(this).maxSizePercent(.20).build()
            }.diskCachePolicy(CachePolicy.ENABLED).diskCache {
                DiskCache.Builder()
                    .directory(
                        applicationContext.cacheDir.resolve("coil_disk_cache")
                    )
                    .maxSizePercent(.05).build()
            }.crossfade(true).build()
    }
}