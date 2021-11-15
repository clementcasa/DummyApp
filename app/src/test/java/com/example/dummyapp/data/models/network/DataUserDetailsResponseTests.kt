package com.example.dummyapp.data.models.network

import com.example.dummyapp.data.exceptions.DataAPIDecodeException
import com.example.dummyapp.data.fixtures.DataFixtures
import com.example.dummyapp.utils.fixtures.entities.DomainFixtures
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class DataUserDetailsResponseTests {
    
    @Test
    fun testToDomain() {
        val expectedData = DataFixtures.DataUserDetailsResponseUtils.create()
        val expectedDomain = DomainFixtures.DomainUserDetailsUtils.create()
        assertThat(expectedData.toDomain(), equalTo(expectedDomain))
    }
    
    @Test
    fun testToDomainWithWrongDateOfBirth() {
        val expectedData = DataFixtures.DataUserDetailsResponseUtils.create().copy(dateOfBirth = "date")
        try {
            expectedData.toDomain()
            fail("toDomain should fail")
        } catch (e: Exception) {
            assertThat(e, equalTo(DataAPIDecodeException("dateOfBirth ${expectedData.dateOfBirth} is not in the right format")))
        }
    }
    
    @Test
    fun testToDomainWithWrongRegisterDate() {
        val expectedData = DataFixtures.DataUserDetailsResponseUtils.create().copy(registerDate = "date")
        try {
            expectedData.toDomain()
            fail("toDomain should fail")
        } catch (e: Exception) {
            assertThat(e, equalTo(DataAPIDecodeException("registerDate ${expectedData.registerDate} is not in the right format")))
        }
    }
}