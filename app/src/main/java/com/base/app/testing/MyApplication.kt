package com.base.app.testing

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.chibatching.kotpref.Kotpref
import com.chibatching.kotpref.gsonpref.gson
import com.google.gson.Gson

class MyApplication : Application() {

    lateinit var mContext: Context

    companion object {
        private lateinit var mInstance: MyApplication

        @Synchronized
        fun getInstance(): MyApplication {
            return mInstance
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        mContext = this

        // Init Shared Preferences
        Kotpref.init(this)
        Kotpref.gson = Gson()
    }

}