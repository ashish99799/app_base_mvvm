package com.base.app.testing.ui.main.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.base.AppBaseAdapter
import com.base.app.testing.databinding.ItemSearchRepoCellBinding
import com.base.app.testing.model.responses.RowData
import com.base.app.testing.util.loadImage


class SearchAdapter : AppBaseAdapter<RowData, ItemSearchRepoCellBinding>() {

    override fun getViewBinding(viewGroup: ViewGroup, attachToRoot: Boolean) = ItemSearchRepoCellBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, attachToRoot)

    /*: ItemSearchRepoCellBinding {
        val mView = ItemSearchRepoCellBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, attachToRoot)
        return DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context), mView.root.id, viewGroup, false)

        return DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context),
            ItemSearchRepoCellBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, attachToRoot).root.id, viewGroup, false
        )

        //ItemSearchRepoCellBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, attachToRoot)
    }*/

    override fun setClickableView(itemView: View): List<View?> = listOf(binding.divCell)

    override fun onBind(
        viewType: Int,
        view: ItemSearchRepoCellBinding,
        position: Int,
        item: RowData,
        payloads: MutableList<Any>?
    ) {
//        val dataBinding: ViewDataBinding = DataBindingUtil.bind(view.root)
        view.run {
            binding.rowData = item
            imgAvatar.loadImage((item.avatar_url ?: ""))
//            lblUserName.text = (item.login ?: "")
        }
    }
}