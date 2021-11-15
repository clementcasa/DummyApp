package com.example.dummyapp.utils.fixtures.entities

import com.example.dummyapp.data.fixtures.FixturesConstants
import com.example.dummyapp.data.utils.toDate
import com.example.dummyapp.domain.models.DomainUserDetails
import com.example.dummyapp.domain.models.DomainUserGender
import com.example.dummyapp.domain.models.DomainUserList
import com.example.dummyapp.domain.models.DomainUserLocation
import com.example.dummyapp.domain.models.DomainUserPreview
import com.example.dummyapp.domain.models.DomainUserTitle

class DomainFixtures {
    
    object DomainUserListUtils {
        fun create(): DomainUserList =
            DomainUserList(
                data = listOf(DomainUserPreviewUtils.create()),
                total = FixturesConstants.UserList.total,
                page = FixturesConstants.UserList.page,
                limit = FixturesConstants.UserList.limit
            )
    }
    
    object DomainUserPreviewUtils {
        fun create(): DomainUserPreview =
            DomainUserPreview(
                id = FixturesConstants.User.id,
                title = DomainUserTitle(FixturesConstants.User.title),
                firstName = FixturesConstants.User.firstName,
                lastName = FixturesConstants.User.lastName,
                picture = FixturesConstants.User.picture
            )
    }
    
    object DomainUserDetailsUtils {
        fun create(): DomainUserDetails =
            DomainUserDetails(
                id = FixturesConstants.User.id,
                title = DomainUserTitle(FixturesConstants.User.title),
                firstName = FixturesConstants.User.firstName,
                lastName = FixturesConstants.User.lastName,
                gender = DomainUserGender(FixturesConstants.User.gender),
                email = FixturesConstants.User.email,
                dateOfBirth = FixturesConstants.User.dateOfBirth.toDate()!!,
                registerDate = FixturesConstants.User.registerDate.toDate()!!,
                phone = FixturesConstants.User.phone,
                picture = FixturesConstants.User.picture,
                location = DomainUserLocationUtils.create()
            )
    }
    
    object DomainUserLocationUtils {
        fun create(): DomainUserLocation =
            DomainUserLocation(
                street = FixturesConstants.Location.street,
                city = FixturesConstants.Location.city,
                state =  FixturesConstants.Location.state,
                country = FixturesConstants.Location.country,
                timezone = FixturesConstants.Location.timezone
            )
    }
}