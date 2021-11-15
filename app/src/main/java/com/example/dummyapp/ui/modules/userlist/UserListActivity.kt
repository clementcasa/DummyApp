package com.example.dummyapp.ui.modules.userlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummyapp.R
import com.example.dummyapp.databinding.ActivityUserListBinding
import com.example.dummyapp.presentation.models.userPreview.UIUserPreviewItem
import com.example.dummyapp.presentation.modules.userlist.UserListPresenter
import com.example.dummyapp.presentation.modules.userlist.UserListView
import com.example.dummyapp.ui.modules.userdetails.UserDetailsActivity
import com.example.dummyapp.ui.modules.userlist.adapter.UserListAdapter
import com.example.dummyapp.ui.modules.userlist.adapter.UserListAdapterDelegate
import com.example.dummyapp.ui.utils.SpaceItemDecoration
import com.example.dummyapp.ui.utils.viewBindings.activityViewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserListActivity: AppCompatActivity(), UserListView, UserListAdapterDelegate {
    
    private val binding by activityViewBinding(ActivityUserListBinding::inflate)
    
    @Inject lateinit var presenter: UserListPresenter
    @Inject lateinit var adapter: UserListAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setupUI()
        setupAdapter()
        presenter.attach(this, lifecycle)
        presenter.refreshData()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    
    private fun setupUI() {
        with(binding) {
            setSupportActionBar(toolbarInclude.toolbar)
            supportActionBar?.run {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
                setDisplayShowTitleEnabled(false)
            }
            toolbarInclude.toolbar.title = getString(R.string.user_list_title)
            refreshLayout.setOnRefreshListener {
                presenter.refreshData()
            }
            loadMoreItemsButton.setOnClickListener {
                presenter.loadMoreItems()
            }
        }
    }
    
    private fun setupAdapter() {
        with(binding.recyclerView) {
            adapter = this@UserListActivity.adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val space = resources.getDimension(R.dimen.userListSpace).toInt()
            addItemDecoration(SpaceItemDecoration(space))
        }
        adapter.delegate = this
    }
    
    //region UserListView callbacks
    override fun onShowLoading() {
        binding.refreshLayout.isRefreshing = true
    }
    
    override fun onHideLoading() {
        binding.refreshLayout.isRefreshing = false
    }
    
    override fun onShowLoadMoreButton() {
        binding.loadMoreItemsContainer.isVisible = true
    }
    
    override fun onHideLoadModeButton() {
        binding.loadMoreItemsContainer.isVisible = false
    }
    
    override fun onShowLoadMoreLoading() {
        with(binding) {
            loadMoreItemsLoadingProgress.isVisible = true
            loadMoreItemsButton.isVisible = false
        }
    }
    
    override fun onHideLoadMoreLoading() {
        with(binding) {
            loadMoreItemsLoadingProgress.isVisible = false
            loadMoreItemsButton.isVisible = true
        }
    }
    
    override fun onReceiveData(userList: List<UIUserPreviewItem>) {
        binding.viewSwitcher.displayedChild = 1
        adapter.submitList(userList)
    }
    
    override fun onShowEmptyView() {
        binding.viewSwitcher.displayedChild = 0
        adapter.submitList(listOf())
    }
    //endregion
    
    //region UserListAdapterDelegate callbacks
    override fun didSelectUser(item: UIUserPreviewItem, viewsToAnimate: List<Pair<View, String>>) {
        val transitionBundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this, *viewsToAnimate.toTypedArray()).toBundle()
        Intent(this, UserDetailsActivity::class.java)
            .apply {
                putExtra(UserDetailsActivity.USER_ID_DATA, item.id)
                putExtra(UserDetailsActivity.USER_PHOTO_DATA, item.picture)
                putExtra(UserDetailsActivity.USER_TITLE_DATA, item.title.getTitleText(this@UserListActivity))
                putExtra(UserDetailsActivity.USER_FULL_NAME_DATA, item.getUserFullName())
            }.also { intent -> startActivity(intent, transitionBundle) }
    }
    //endregion
}