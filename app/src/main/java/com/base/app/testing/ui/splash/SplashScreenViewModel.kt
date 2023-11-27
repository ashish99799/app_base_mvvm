package com.base.app.testing.ui.splash

import android.content.Context
import androidx.lifecycle.LiveData
import com.application.base.AppBaseViewModel
import com.base.app.testing.MyApplication.Companion.appDB
import com.base.app.testing.model.db.GymData
import com.base.app.testing.model.db.PopularGymData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashScreenViewModel : AppBaseViewModel() {

    companion object {
        lateinit var mContext: Context

        @JvmStatic
        fun newInstance(context: Context) = SplashScreenViewModel().apply {
            mContext = context
        }

        lateinit var mGymDataList: LiveData<List<GymData>>
    }

    fun getAllGymList(): LiveData<List<GymData>> {
        mGymDataList = appDB.daoApp().getAllGymList()
        return mGymDataList
    }

    suspend fun addAllGym(gymData: List<GymData>) {
        withContext(Dispatchers.IO) {
            gymData.forEach {
                insertGymData(it)
            }
        }
    }

    suspend fun addAllPopularGym(popularGymData: List<PopularGymData>) {
        withContext(Dispatchers.IO) {
            popularGymData.forEach {
                insertPopularGym(it)
            }
        }
    }

    // ================================================================================================
    // Gym Data Start
    private fun insertGymData(gymData: GymData) {
        CoroutineScope(Dispatchers.IO).launch {
            appDB.daoApp().addGym(gymData)
        }
    }

    // Popular Gym Data Start
    private fun insertPopularGym(popularGymData: PopularGymData) {
        CoroutineScope(Dispatchers.IO).launch {
            appDB.daoApp().addPopularGym(popularGymData)
        }
    }
    // ================================================================================================
}