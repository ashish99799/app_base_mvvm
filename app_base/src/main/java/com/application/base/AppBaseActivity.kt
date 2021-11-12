package com.application.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

// VB | VM => Template types
abstract class AppBaseActivity<VB : ViewBinding, VM : AppBaseViewModel>(private val setLanguage: Boolean? = false) : AppCompatActivity() {

    lateinit var binding: VB
    abstract fun setViewBinding(): VB

    lateinit var viewModel: VM
    abstract fun setViewModel(): VM

    abstract fun initView()
    abstract fun initOnClick()

    protected val activityLauncherKt = AppBaseActivityResultKt.registerActivityForResult(this)
    /*
    // For request result -> Current Activity
    activityLauncherKt.launch(Intent(this, MainActivityResult::class.java), object : OnActivityResultKt<ActivityResult> {
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
    })

    // For send result -> Second Activity
    setResult(RESULT_OK, Intent().apply {
        putExtra("INTENT_DATA", "Success")
    }).also {
        finish()
    }
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        if (setLanguage == true) {
            bindLanguage()
        }
        super.onCreate(savedInstanceState)
        binding = setViewBinding()
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(setViewModel()::class.java)
        initView()
        initOnClick()
    }

    fun hideKeyboard(mView: View? = null) {
        val view = ((mView ?: currentFocus) ?: View(this))
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showKeyboard(mView: View? = null) {
        var view = ((mView ?: currentFocus))
        if (view != null) {
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInputFromWindow(view.windowToken, InputMethodManager.SHOW_FORCED, 0)
        } else {
            this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        }
    }

}