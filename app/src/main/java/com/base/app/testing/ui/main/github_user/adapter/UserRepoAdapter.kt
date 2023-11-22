package com.base.app.testing.ui.main.github_user.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.base.AppBaseFilterAdapter
import com.base.app.testing.databinding.ItemGithubUserCellBinding
import com.base.app.testing.model.responses.UserRepoData

class UserRepoAdapter : AppBaseFilterAdapter<UserRepoData, ItemGithubUserCellBinding>() {

    override fun getViewBinding(parent: ViewGroup, attachToRoot: Boolean) = ItemGithubUserCellBinding.inflate(LayoutInflater.from(parent.context), parent, attachToRoot)

    override fun setClickableView(itemView: View): List<View?> = listOf(binding.divCell)

    override fun includeItem(query: CharSequence?, item: UserRepoData): Boolean {
        return ((item.name ?: "").lowercase().contains(query.toString().lowercase()))
    }

    override fun onBind(
        viewType: Int,
        view: ItemGithubUserCellBinding,
        position: Int,
        item: UserRepoData,
        payloads: MutableList<Any>?
    ) {
        view.run {
            lblRepoName.text = (item.name ?: "")
            lblForks.text = (item.forks ?: 0).toString().plus(" Forks")
            lblStars.text = (item.stargazers_count ?: 0).toString().plus(" Stars")
        }
    }

}