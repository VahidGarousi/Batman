package ir.vbile.app.batman.core

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import dagger.hilt.android.HiltAndroidApp
import ir.vbile.app.batman.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class BatmanBaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}