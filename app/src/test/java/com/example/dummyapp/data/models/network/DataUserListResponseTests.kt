package com.example.dummyapp.data.models.network

import com.example.dummyapp.data.fixtures.DataFixtures
import com.example.dummyapp.utils.fixtures.entities.DomainFixtures
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataUserListResponseTests {
    
    @Test
    fun testToDomain() {
        val expectedData = DataFixtures.DataUserListResponseUtils.create()
        val expectedDomain = DomainFixtures.DomainUserListUtils.create()
        assertThat(expectedData.toDomain(), equalTo(expectedDomain))
    }
}