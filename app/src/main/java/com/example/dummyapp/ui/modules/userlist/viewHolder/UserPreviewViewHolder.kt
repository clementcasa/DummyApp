package com.example.dummyapp.ui.modules.userlist.viewHolder

import android.view.View
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyapp.R
import com.example.dummyapp.databinding.ViewHolderUserPreviewBinding
import com.example.dummyapp.presentation.models.userPreview.UIUserPreviewItem
import com.example.dummyapp.ui.modules.userlist.adapter.UserListAdapterDelegate
import com.example.dummyapp.ui.utils.loadImageFromUrl

class UserPreviewViewHolder(
    private val binding: ViewHolderUserPreviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: UIUserPreviewItem, delegate: UserListAdapterDelegate?) {
        with(binding) {
            userTitleTextView.text = item.title.getTitleText(root.context)
            userNameTextView.text = item.getUserFullName()
            userImageView.loadImageFromUrl(R.drawable.ic_launcher_foreground, item.picture)
            cardView.setOnClickListener {
                delegate?.didSelectUser(item, listOf(
                    Pair<View, String>(userImageView, root.context.getString(R.string.transition_user_photo)),
                    Pair<View, String>(userTitleTextView, root.context.getString(R.string.transition_user_title)),
                    Pair<View, String>(userNameTextView, root.context.getString(R.string.transition_user_full_name))
                ))
            }
        }
    }
}