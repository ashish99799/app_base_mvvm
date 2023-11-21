package com.base.app.testing

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.base.app.testing.model.db.AppDB
import com.base.app.testing.model.repositorys.AppRepository
import com.chibatching.kotpref.Kotpref
import com.chibatching.kotpref.gsonpref.gson
import com.google.gson.Gson

class MyApplication : Application() {

    lateinit var mContext: Context
    companion object {
        private lateinit var mInstance: MyApplication
        lateinit var appDB: AppDB
        lateinit var appRepository: AppRepository

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

        // Init -> App DB & App Repository
        appDB = AppDB.getDatabaseClient(this)
        appRepository = AppRepository()

        // Init Shared Preferences
        Kotpref.init(this)
        Kotpref.gson = Gson()
    }

}