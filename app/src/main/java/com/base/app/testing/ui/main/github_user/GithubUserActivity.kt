package com.base.app.testing.ui.main.github_user

import android.annotation.SuppressLint
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.application.base.AppBaseActivity
import com.base.app.testing.R
import com.base.app.testing.databinding.ActivityGithubUserBinding
import com.base.app.testing.model.responses.RowData
import com.base.app.testing.ui.main.github_user.adapter.UserRepoAdapter
import com.base.app.testing.util.INTENT_DATA
import com.base.app.testing.util.loadImage
import java.text.SimpleDateFormat
import java.util.*

class GithubUserActivity : AppBaseActivity<ActivityGithubUserBinding, GithubUserViewModel>(), SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener {

    var rowData: RowData? = null
    lateinit var userRepoAdapter: UserRepoAdapter

    // View Binding
    override fun setViewBinding() = ActivityGithubUserBinding.inflate(layoutInflater)

    // Attach ViewModel
    override fun setViewModel() = GithubUserViewModel.newInstance(this)

    override fun initView() {
        rowData = intent.getParcelableExtra(INTENT_DATA)

        if (rowData == null) {
            onBackPressed()
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = (rowData!!.login ?: "")

        binding.imgAvatar.loadImage((rowData!!.avatar_url ?: ""))
        binding.lblUserName.text = (rowData!!.login ?: "")

        userRepoAdapter = UserRepoAdapter()
        binding.rvUserRepo.apply {
            adapter = userRepoAdapter.also {
                it.addAll(arrayListOf())
            }
        }

        binding.searchView.setOnQueryTextListener(this)
        (binding.searchView.findViewById(androidx.appcompat.R.id.search_plate) as View).setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite))

        binding.swipeRefreshLayout.setOnRefreshListener(this)
        binding.swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent)
        binding.swipeRefreshLayout.isRefreshing = true

        getUserInfo()
        onRefresh()
    }

    override fun initOnClick() {

    }

    @SuppressLint("SimpleDateFormat")
    private fun getUserInfo() {
        viewModel.onUserInfo((rowData!!.login ?: "")) { it, data ->
            binding.lblUserName.text = (it.login ?: "")
            binding.lblEmail.text = ("Email : ").plus(it.email ?: "")
            binding.lblLocation.text = ("Location : ").plus(it.location ?: "")
            binding.lblFollowers.text = (it.followers ?: 0).toString().plus(" Followers")
            binding.lblFollowing.text = ("Following ").plus(it.following ?: 0)

            val inputFormat = SimpleDateFormat(("yyyy-MM-dd'T'HH:mm:ss'Z'"))
            val outputFormat = SimpleDateFormat(("dd-MM-yyyy"))

            val my_date = it.created_at
            val date: Date = inputFormat.parse(my_date)

            binding.lblJoinDate.text = ("Join Date : ").plus(outputFormat.format(date))
        }
    }

    override fun onRefresh() {
        getUserRepo()
    }

    private fun getUserRepo() {
        viewModel.onUserRepo((rowData!!.login ?: ""), binding.swipeRefreshLayout) {
            userRepoAdapter.addAll(it)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        userRepoAdapter.filter.filter(newText)
        return false
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}