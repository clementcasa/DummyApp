package com.example.dummyapp.ui.modules.userlist.viewHolder

import androidx.recyclerview.widget.DiffUtil
import com.example.dummyapp.presentation.models.userPreview.UIUserPreviewItem
import javax.inject.Inject

class UIUserPreviewItemDiffUtils @Inject constructor() : DiffUtil.ItemCallback<UIUserPreviewItem>() {
    
    override fun areItemsTheSame(oldItem: UIUserPreviewItem, newItem: UIUserPreviewItem): Boolean =
        oldItem == newItem
    
    override fun areContentsTheSame(oldItem: UIUserPreviewItem, newItem: UIUserPreviewItem): Boolean =
        oldItem == newItem
}