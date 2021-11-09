package com.example.dummyapp.presentation.models.userLocation

import com.example.dummyapp.utils.fixtures.entities.DomainFixtures
import com.example.dummyapp.utils.fixtures.entities.PresenterFixtures
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UIUserLocationItemMappingTests {
    
    @Test
    fun testToUIItem() {
        val expectedDomain = DomainFixtures.DomainUserLocationUtils.create()
        val expectedUIItem = PresenterFixtures.UIUserLocationItemUtils.create()
        assertThat(expectedDomain.toUIItem(), equalTo(expectedUIItem))
    }
}