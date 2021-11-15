package com.example.dummyapp.data.models.database

import com.example.dummyapp.data.exceptions.DataAPIDecodeException
import com.example.dummyapp.data.fixtures.DataFixtures
import com.example.dummyapp.data.fixtures.FixturesConstants
import com.example.dummyapp.utils.fixtures.entities.DomainFixtures
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class UserEntityTests {
    
    @Test
    fun testToDomainUserList() {
        val expectedData = listOf(DataFixtures.UserEntityUtils.createFromDetails())
        val expectedDomain = DomainFixtures.DomainUserListUtils.create()
        assertThat(expectedData.toDomainUserList(FixturesConstants.UserList.page, FixturesConstants.UserList.limit), equalTo(expectedDomain))
    }
    
    @Test
    fun testToDomainUserDetails() {
        val expectedData = DataFixtures.UserEntityUtils.createFromDetails()
        val expectedDomain = DomainFixtures.DomainUserDetailsUtils.create()
        assertThat(expectedData.toDomainUserDetails(), equalTo(expectedDomain))
    }
    
    @Test
    fun testToDomainUserDetailsWithWrongDateOfBirth() {
        val expectedData = DataFixtures.UserEntityUtils.createFromDetails().copy(dateOfBirth = "date")
        try {
            expectedData.toDomainUserDetails()
            fail("toDomain should fail")
        } catch (e: Exception) {
            assertThat(e, equalTo(DataAPIDecodeException("dateOfBirth ${expectedData.dateOfBirth} is not in the right format")))
        }
    }
    
    @Test
    fun testToDomainUserDetailsWithWrongRegisterDate() {
        val expectedData = DataFixtures.UserEntityUtils.createFromDetails().copy(registerDate = "date")
        try {
            expectedData.toDomainUserDetails()
            fail("toDomain should fail")
        } catch (e: Exception) {
            assertThat(e, equalTo(DataAPIDecodeException("registerDate ${expectedData.registerDate} is not in the right format")))
        }
    }
    
    @Test
    fun testToDomainUserDetailsWithMissingData() {
        val expectedData = DataFixtures.UserEntityUtils.createFromDetails().copy(gender = null, email = null, phone = null, location = null)
        try {
            expectedData.toDomainUserDetails()
            fail("toDomain should fail")
        } catch (e: Exception) {
            assertThat(e, equalTo(DataAPIDecodeException("user info are incomplete")))
        }
    }
    
    @Test
    fun testToDomainUserPreview() {
        val expectedData = DataFixtures.UserEntityUtils.createFromPreview()
        val expectedDomain = DomainFixtures.DomainUserPreviewUtils.create()
        assertThat(expectedData.toDomainUserPreview(), equalTo(expectedDomain))
    }
}