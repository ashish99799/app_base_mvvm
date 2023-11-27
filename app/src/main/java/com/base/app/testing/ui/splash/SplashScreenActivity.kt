package com.base.app.testing.ui.splash

import android.os.Handler
import android.os.Looper
import com.application.base.AppBaseActivity
import com.base.app.testing.R
import com.base.app.testing.databinding.ActivitySplashScreenBinding
import com.base.app.testing.model.db.GymData
import com.base.app.testing.model.db.PopularGymData
import com.base.app.testing.model.responses.GymList
import com.base.app.testing.ui.main.dashboard.DashboardActivity
import com.base.app.testing.util.getJsonDataFromAsset
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask

class SplashScreenActivity : AppBaseActivity<ActivitySplashScreenBinding, SplashScreenViewModel>() {

    override fun setViewBinding() = ActivitySplashScreenBinding.inflate(layoutInflater)

    override fun setViewModel() = SplashScreenViewModel.newInstance(this)

    lateinit var gymList: ArrayList<GymList>
    private val handler = Handler(Looper.getMainLooper())

    override fun initView() {
        gymList = getJsonDataFromAsset(("data.json"))
        viewModel.getAllGymList().observe(this) {
            if (it.isNullOrEmpty()) {
                loadDataFromJson()
            } else {
                handler.postDelayed(runnable, 3000)
            }
        }
    }

    override fun initOnClick() {

    }

    private val runnable = Runnable {
        startActivity(intentFor<DashboardActivity>().clearTask().newTask())
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun onDestroy() {
        handler.removeCallbacks(runnable)
        super.onDestroy()
    }

    private fun loadDataFromJson() {
        val mGymList = ArrayList<GymData>()
        val mPopularGymList = ArrayList<PopularGymData>()
        gymList.forEach {
            mGymList.add(
                GymData(
                    it.id,
                    it.title ?: "",
                    it.date_time ?: "",
                    it.image ?: "",
                    it.favorite ?: false,
                    it.price ?: 0.0,
                    it.rating ?: 0.0
                )
            )

            (it.popular_gym ?: arrayListOf()).forEach { pop ->
                mPopularGymList.add(
                    PopularGymData(
                        pop.id,
                        it.id,
                        pop.title ?: "",
                        pop.description ?: "",
                        pop.favorite ?: false,
                        pop.price ?: 0.0,
                        pop.rating ?: 0.0,
                        pop.location ?: "",
                        pop.image ?: ""
                    )
                )
            }
        }

        MainScope().launch(Dispatchers.IO) {
            viewModel.addAllGym(mGymList)
            viewModel.addAllPopularGym(mPopularGymList)
        }.invokeOnCompletion {
            handler.postDelayed(runnable, 2000)
        }
    }
}