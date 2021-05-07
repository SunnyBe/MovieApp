package com.buchi.fullentry

import android.app.Application
import com.facebook.stetho.Stetho
import io.shipbook.shipbooksdk.Log
import io.shipbook.shipbooksdk.ShipBook

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}