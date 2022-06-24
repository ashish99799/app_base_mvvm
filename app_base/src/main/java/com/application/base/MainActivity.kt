package com.application.base

/*
import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResult
import com.application.base.databinding.ActivityMainBinding
import org.jetbrains.anko.toast

class MainActivity : AppBaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    override fun setViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun setViewModel() = MainActivityViewModel.newInstance(this)

    override fun initView() {
        activityLauncherKt.launch(
            Intent(this, MainActivityResult::class.java),
            object : AppBaseActivityResultKt.OnActivityResultKt<ActivityResult> {
                override fun onActivityResult(result: ActivityResult) {
                    result.apply {
                        if (resultCode == Activity.RESULT_OK) {
                            if (data != null) {
                                if (data!!.hasExtra("INTENT_DATA")) {
                                    val intData = (data!!.getStringExtra("INTENT_DATA") ?: "")
                                    toast(intData)
                                }
                            }
                        }
                    }
                }
            }
        )
    }

    override fun initOnClick() {
        binding.root.setOnClickListener {
            showKeyboard(it)
        }
        binding.lblHelloWorld.setOnClickListener {
            hideKeyboard(it)
        }
    }

}*/
