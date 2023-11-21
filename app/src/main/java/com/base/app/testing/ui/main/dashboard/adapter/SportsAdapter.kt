package com.base.app.testing.ui.main.dashboard.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.application.base.AppBaseAdapter
import com.application.base.R
import com.application.base.databinding.ItemSportsCellBinding

class SportsAdapter(val mContext: Context) : AppBaseAdapter<String, ItemSportsCellBinding>() {

    override fun getViewBinding(parent: ViewGroup, attachToRoot: Boolean) = ItemSportsCellBinding.inflate(LayoutInflater.from(parent.context), parent, attachToRoot)

    override fun setClickableView(itemView: View): List<View?> = listOf()

    override fun onBind(
        viewType: Int,
        view: ItemSportsCellBinding,
        position: Int,
        item: String,
        payloads: MutableList<Any>?
    ) {
        view.run {
            if (position % 2 == 0) {
                imgSport.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.colorPrimary))
                imgSport.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.colorWhite))
            } else {
                imgSport.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.colorWhite))
                imgSport.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.colorPrimary))
            }
        }
    }
}