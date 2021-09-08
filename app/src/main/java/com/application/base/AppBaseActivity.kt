package com.application.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

// VB | VM => Template types
abstract class AppBaseActivity<VB : ViewBinding, VM : AppBaseViewModel>(val setLanguage: Boolean? = false) : AppCompatActivity() {

    lateinit var binding: VB
    abstract fun setViewBinding(): VB

    lateinit var viewModel: VM
    abstract fun setViewModel(): VM

    abstract fun initView()
    abstract fun initOnClick()

    protected val activityLauncher = AppBaseActivityResult.registerActivityForResult(this)
    /*activityLauncher.launch(Intent(this, MyCouponsByVendorActivity::class.java).apply {
        putExtra(VENDOR_DATA, qrCodeData)
    }) {
        it.apply {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    if (data!!.hasExtra(SELECTED_COUPON)) {
                        selectedCoupon = data!!.getParcelableExtra(SELECTED_COUPON)
                        if (selectedCoupon != null) {
                            createUserOrder()
                        }
                    }
                }
            }
        }
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (setLanguage == true) {
            bindLanguage()
        }
        binding = setViewBinding()
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(setViewModel()::class.java)
        initView()
        initOnClick()
    }

    fun hideKeyboard() {
        var view = currentFocus
        if (view == null) {
            view = View(this)
        }
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showKeyboard() {
        var view = currentFocus
        if (view == null) {
            view = View(this)
        }
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInputFromWindow(
            view.windowToken,
            InputMethodManager.SHOW_FORCED, 0
        )
    }

}