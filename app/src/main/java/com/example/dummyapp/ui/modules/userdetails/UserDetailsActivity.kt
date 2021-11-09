package com.example.dummyapp.ui.modules.userdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.dummyapp.R
import com.example.dummyapp.databinding.ActivityUserDetailsBinding
import com.example.dummyapp.presentation.models.userDetails.UIUserDetailsItem
import com.example.dummyapp.presentation.modules.userdetails.UserDetailsPresenter
import com.example.dummyapp.presentation.modules.userdetails.UserDetailsView
import com.example.dummyapp.ui.utils.getFormatWithDayMonthAndYear
import com.example.dummyapp.ui.utils.loadImageFromUrl
import com.example.dummyapp.ui.utils.viewBindings.activityViewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailsActivity: AppCompatActivity(), UserDetailsView {
    
    private val binding by activityViewBinding(ActivityUserDetailsBinding::inflate)
    
    @Inject lateinit var presenter: UserDetailsPresenter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val userId = intent.getStringExtra(USER_ID_DATA)
        val userPhoto = intent.getStringExtra(USER_PHOTO_DATA)
        val userTitle = intent.getStringExtra(USER_TITLE_DATA)
        val userFullName = intent.getStringExtra(USER_FULL_NAME_DATA)
        
        if (userId != null && userFullName != null) {
            setupUI(userPhoto, userTitle, userFullName)
            presenter.attach(this, lifecycle)
            presenter.setupWithData(userId)
        } else {
            finish()
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    
    private fun setupUI(userPhoto: String?, userTitle: String?, userFullName: String) {
        with(binding) {
            setSupportActionBar(toolbarInclude.toolbar)
            supportActionBar?.run {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
                setDisplayShowTitleEnabled(false)
            }
            toolbarInclude.toolbar.title = getString(R.string.user_details_title)
            
            userImageView.loadImageFromUrl(R.drawable.ic_launcher_foreground, userPhoto)
            userTitleTextView.text = userTitle
            userFullNameTextView.text = userFullName
            
            infoButton.setOnClickListener {
                infoIndicatorView.isVisible = true
                addressIndicatorView.isInvisible = true
                userInfoViewSwitcher.displayedChild = 0
            }
    
            addressButton.setOnClickListener {
                infoIndicatorView.isInvisible = true
                addressIndicatorView.isVisible = true
                userInfoViewSwitcher.displayedChild = 1
            }
        }
    }
    
    //region UserDetailsView callbacks
    override fun onShowLoading() {
        with(binding) {
            loadingContainer.isVisible = true
            userInfoViewSwitcher.isVisible = false
            infoButton.isEnabled = false
            addressButton.isEnabled = false
        }
    }
    
    override fun onHideLoading() {
        with(binding) {
            loadingContainer.isVisible = false
            userInfoViewSwitcher.isVisible = true
            infoButton.isEnabled = true
            addressButton.isEnabled = true
        }
    }
    
    override fun onReceiveUserDetails(item: UIUserDetailsItem) {
        with(binding) {
            val genderTitle = item.gender.getGenderTitle(root.context)
            if (genderTitle != null) {
                userGenderTextView.text = genderTitle
            } else {
                genderContainer.isVisible = false
            }
            userDateOfBirthTextView.text = item.dateOfBirth.getFormatWithDayMonthAndYear(root.context)
            userRegistrationDateTextView.text = item.registerDate.getFormatWithDayMonthAndYear(root.context)
            userEmailTextView.text = item.email
            userPhoneTextView.text = item.phone
            userAddressTextView.text = item.location.getAddress()
        }
    }
    //endregion
    
    companion object {
        const val USER_ID_DATA = "user_id_data"
        const val USER_PHOTO_DATA = "user_photo_data"
        const val USER_TITLE_DATA = "user_title_data"
        const val USER_FULL_NAME_DATA = "user_full_name_data"
    }
}