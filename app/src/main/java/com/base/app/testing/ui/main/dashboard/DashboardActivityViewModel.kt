package com.base.app.testing.ui.main.dashboard

import android.content.Context
import androidx.lifecycle.LiveData
import com.application.base.AppBaseViewModel
import com.base.app.testing.MyApplication.Companion.appDB
import com.base.app.testing.model.db.GymData
import com.base.app.testing.model.db.PopularGymData
import com.base.app.testing.util.LoadingViews

class DashboardActivityViewModel : AppBaseViewModel(), LoadingViews {

    companion object {
        lateinit var mContext: Context

        @JvmStatic
        fun newInstance(context: Context) = DashboardActivityViewModel().apply {
            mContext = context
        }

        lateinit var mGymDataList: LiveData<List<GymData>>
    }

    fun getAllGymList(): LiveData<List<GymData>> {
        mGymDataList = appDB.daoApp().getAllGymList()
        return mGymDataList
    }

    fun updateGym(gymData: GymData) {
        appDB.daoApp().updateGym(gymData)
    }

    fun getAllPopularGyms(): LiveData<List<PopularGymData>> {
        return appDB.daoApp().getAllPopularGyms()
    }

    fun getAllPopularGymList(gym_id: Int): LiveData<List<PopularGymData>> {
        return appDB.daoApp().getAllPopularGymList(gym_id)
    }

    fun updatePopularGym(popularGymData: PopularGymData) {
        appDB.daoApp().updatePopularGym(popularGymData)
    }

}