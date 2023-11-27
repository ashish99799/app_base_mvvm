package com.application.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

// VB | VM => Template types
abstract class AppBaseFragment<VB : ViewBinding, VM : AppBaseViewModel>(private val setLanguage: Boolean? = false) : Fragment() {

    private lateinit var mContext: Context
    private lateinit var mActivity: Activity

    lateinit var binding: VB
    abstract fun getViewBinding(): VB

    lateinit var viewModel: VM
    abstract fun getViewModelClass(): VM

    private val disposableContainer = CompositeDisposable()

    protected val activityLauncher = AppBaseActivityResultKt.registerActivityForResult(this)
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
        if (setLanguage == true) {
            requireContext().setLocale(requireContext().getLanguage())
        }
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
        mContext = requireContext()
        mActivity = requireActivity()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = getViewBinding()
        viewModel = ViewModelProvider(requireActivity())[getViewModelClass()::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeData()
    }

    open fun setUpViews() {}

    open fun observeView() {}

    open fun observeData() {}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AppBaseActivity<*, *>) {
            val activity = context as AppBaseActivity<*, *>?
            activity?.let { this.mActivity = it }
        }
    }

    fun Disposable.addToContainer() = disposableContainer.add(this)

    override fun onDestroyView() {
        disposableContainer.clear()
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
        if (mContext is AppBaseActivity<*, *>) {
            val activity = mContext as AppBaseActivity<*, *>?
            activity?.let { this.mActivity = it }
        }
    }

    open fun hideKeyboardFrom(context: Context, view: View) {
        val imm: InputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    open fun showKeyboard(context: Context, view: View) {
        val inputMethodManager = context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInputFromWindow(view.windowToken, InputMethodManager.SHOW_FORCED, 0)
    }

}