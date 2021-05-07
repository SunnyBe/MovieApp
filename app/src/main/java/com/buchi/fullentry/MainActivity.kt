package com.buchi.fullentry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.shipbook.shipbooksdk.Log

class MainActivity : AppCompatActivity() {
    private val TAG: String = javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "Activity Stater log")
        Log.e(TAG, "Activity Stater error log")
    }
}