package com.example.dummyapp.data.models.network

import com.example.dummyapp.data.fixtures.DataFixtures
import com.example.dummyapp.utils.fixtures.entities.DomainFixtures
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataUserPreviewResponseTests {
    
    @Test
    fun testToDomain() {
        val expectedData = DataFixtures.DataUserPreviewResponseUtils.create()
        val expectedDomain = DomainFixtures.DomainUserPreviewUtils.create()
        assertThat(expectedData.toDomain(), equalTo(expectedDomain))
    }
    
    @Test
    fun testToEntity() {
        val expectedPage = 1
        val expectedData = DataFixtures.DataUserPreviewResponseUtils.create()
        val expectedEntity = DataFixtures.UserEntityUtils.createFromPreview().copy(page = expectedPage)
        assertThat(expectedData.toEntity(expectedPage), equalTo(expectedEntity))
    }
}