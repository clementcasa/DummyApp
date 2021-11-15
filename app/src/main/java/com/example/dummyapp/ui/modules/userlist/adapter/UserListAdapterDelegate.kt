package com.example.dummyapp.ui.modules.userlist.adapter

import androidx.core.util.Pair
import android.view.View
import com.example.dummyapp.presentation.models.userPreview.UIUserPreviewItem

interface UserListAdapterDelegate {
    fun didSelectUser(item: UIUserPreviewItem, viewsToAnimate: List<Pair<View, String>>)
}