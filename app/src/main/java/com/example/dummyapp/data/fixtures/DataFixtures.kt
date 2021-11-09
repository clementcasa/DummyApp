package com.example.dummyapp.data.fixtures

import com.example.dummyapp.data.models.database.UserEntity
import com.example.dummyapp.data.models.network.DataUserDetailsResponse
import com.example.dummyapp.data.models.network.DataUserListResponse
import com.example.dummyapp.data.models.network.DataUserLocationResponse
import com.example.dummyapp.data.models.network.DataUserPreviewResponse

class DataFixtures {
    
    object UserEntityUtils {
        fun createFromDetails(): UserEntity =
            UserEntity(
                id = FixturesConstants.User.id,
                page = FixturesConstants.UserList.page,
                title = FixturesConstants.User.title,
                firstName = FixturesConstants.User.firstName,
                lastName = FixturesConstants.User.lastName,
                gender = FixturesConstants.User.gender,
                email = FixturesConstants.User.email,
                dateOfBirth = FixturesConstants.User.dateOfBirth,
                registerDate = FixturesConstants.User.registerDate,
                phone = FixturesConstants.User.phone,
                picture = FixturesConstants.User.picture,
                location = DataUserLocationResponseUtils.create()
            )
    
        fun createFromPreview(): UserEntity =
            UserEntity(
                id = FixturesConstants.User.id,
                page = FixturesConstants.UserList.page,
                title = FixturesConstants.User.title,
                firstName = FixturesConstants.User.firstName,
                lastName = FixturesConstants.User.lastName,
                gender = null,
                email = null,
                dateOfBirth = null,
                registerDate = null,
                phone = null,
                picture = FixturesConstants.User.picture,
                location = null
            )
    }
    
    object DataUserListResponseUtils {
        fun create(): DataUserListResponse =
            DataUserListResponse(
                data = listOf(DataUserPreviewResponseUtils.create()),
                total = FixturesConstants.UserList.total,
                page = FixturesConstants.UserList.page,
                limit = FixturesConstants.UserList.limit
            )
    
        fun createRandom(): DataUserListResponse =
            DataUserListResponse(
                data = createRandomUserPreviews(),
                total = FixturesConstants.UserList.total,
                page = FixturesConstants.UserList.page,
                limit = FixturesConstants.UserList.limit
            )
        
        private fun createRandomUserPreviews(): List<DataUserPreviewResponse> {
            val array = ArrayList<DataUserPreviewResponse>()
            for (i in (0..(0..5).random())) {
                array.add(DataUserPreviewResponseUtils.createRandom())
            }
            return array
        }
    }
    
    object DataUserPreviewResponseUtils {
        fun create(): DataUserPreviewResponse =
            DataUserPreviewResponse(
                id = FixturesConstants.User.id,
                title = FixturesConstants.User.title,
                firstName = FixturesConstants.User.firstName,
                lastName = FixturesConstants.User.lastName,
                picture = FixturesConstants.User.picture
            )
    
        fun createRandom(): DataUserPreviewResponse =
            DataUserPreviewResponse(
                id = FixturesConstants.User.id,
                title = RandomFixturesConstants.getRandomUserTitle(),
                firstName = RandomFixturesConstants.getRandomFirstName(),
                lastName = RandomFixturesConstants.getRandomLastName(),
                picture = RandomFixturesConstants.getRandomPhoto()
            )
    }
    
    object DataUserDetailsResponseUtils {
        fun create(): DataUserDetailsResponse =
            DataUserDetailsResponse(
                id = FixturesConstants.User.id,
                title = FixturesConstants.User.title,
                firstName = FixturesConstants.User.firstName,
                lastName = FixturesConstants.User.lastName,
                gender = FixturesConstants.User.gender,
                email = FixturesConstants.User.email,
                dateOfBirth = FixturesConstants.User.dateOfBirth,
                registerDate = FixturesConstants.User.registerDate,
                phone = FixturesConstants.User.phone,
                picture = FixturesConstants.User.picture,
                location = DataUserLocationResponseUtils.create()
            )
    
        fun createRandom(): DataUserDetailsResponse =
            DataUserDetailsResponse(
                id = FixturesConstants.User.id,
                title = RandomFixturesConstants.getRandomUserTitle(),
                firstName = RandomFixturesConstants.getRandomFirstName(),
                lastName = RandomFixturesConstants.getRandomLastName(),
                gender = RandomFixturesConstants.getRandomUserGender(),
                email = FixturesConstants.User.email,
                dateOfBirth = FixturesConstants.User.dateOfBirth,
                registerDate = FixturesConstants.User.registerDate,
                phone = FixturesConstants.User.phone,
                picture = RandomFixturesConstants.getRandomPhoto(),
                location = DataUserLocationResponseUtils.create()
            )
    }
    
    object DataUserLocationResponseUtils {
        fun create(): DataUserLocationResponse =
            DataUserLocationResponse(
                street = FixturesConstants.Location.street,
                city = FixturesConstants.Location.city,
                state =  FixturesConstants.Location.state,
                country = FixturesConstants.Location.country,
                timezone = FixturesConstants.Location.timezone
            )
    }
}