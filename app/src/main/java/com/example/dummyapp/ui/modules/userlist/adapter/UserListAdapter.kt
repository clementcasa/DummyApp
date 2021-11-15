package com.example.dummyapp.ui.modules.userlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.dummyapp.databinding.ViewHolderUserPreviewBinding
import com.example.dummyapp.presentation.models.userPreview.UIUserPreviewItem
import com.example.dummyapp.ui.modules.userlist.viewHolder.UIUserPreviewItemDiffUtils
import com.example.dummyapp.ui.modules.userlist.viewHolder.UserPreviewViewHolder
import javax.inject.Inject

class UserListAdapter @Inject constructor(
    diffUtil: UIUserPreviewItemDiffUtils
) : ListAdapter<UIUserPreviewItem, UserPreviewViewHolder>(diffUtil) {
    
    var delegate: UserListAdapterDelegate? = null
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPreviewViewHolder =
        UserPreviewViewHolder(ViewHolderUserPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    
    override fun onBindViewHolder(holder: UserPreviewViewHolder, position: Int) {
        holder.onBind(getItem(position), delegate)
    }
}