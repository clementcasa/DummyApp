package com.example.dummyapp.utils.fixtures.entities

import com.example.dummyapp.data.fixtures.FixturesConstants
import com.example.dummyapp.data.utils.toDate
import com.example.dummyapp.domain.models.DomainUserDetails
import com.example.dummyapp.domain.models.DomainUserGender
import com.example.dummyapp.domain.models.DomainUserLocation
import com.example.dummyapp.domain.models.DomainUserTitle
import com.example.dummyapp.presentation.models.userDetails.UIUserDetailsItem
import com.example.dummyapp.presentation.models.userGender.UIUserGenderItem
import com.example.dummyapp.presentation.models.userLocation.UIUserLocationItem
import com.example.dummyapp.presentation.models.userPreview.UIUserPreviewItem
import com.example.dummyapp.presentation.models.userTitle.UIUserTitleItem

class PresenterFixtures {
    
    object UIUserPreviewItemUtils {
        fun create(): UIUserPreviewItem =
            UIUserPreviewItem(
                id = FixturesConstants.User.id,
                title = UIUserTitleItem(FixturesConstants.User.title),
                firstName = FixturesConstants.User.firstName,
                lastName = FixturesConstants.User.lastName,
                picture = FixturesConstants.User.picture
            )
    }
    
    object UIUserDetailsItemUtils {
        fun create(): UIUserDetailsItem =
            UIUserDetailsItem(
                id = FixturesConstants.User.id,
                title = UIUserTitleItem(FixturesConstants.User.title),
                firstName = FixturesConstants.User.firstName,
                lastName = FixturesConstants.User.lastName,
                gender = UIUserGenderItem(FixturesConstants.User.gender),
                email = FixturesConstants.User.email,
                dateOfBirth = FixturesConstants.User.dateOfBirth.toDate()!!,
                registerDate = FixturesConstants.User.registerDate.toDate()!!,
                phone = FixturesConstants.User.phone,
                picture = FixturesConstants.User.picture,
                location = UIUserLocationItemUtils.create()
            )
    }
    
    object UIUserLocationItemUtils {
        fun create(): UIUserLocationItem =
                UIUserLocationItem(
                street = FixturesConstants.Location.street,
                city = FixturesConstants.Location.city,
                state =  FixturesConstants.Location.state,
                country = FixturesConstants.Location.country
            )
    }
}