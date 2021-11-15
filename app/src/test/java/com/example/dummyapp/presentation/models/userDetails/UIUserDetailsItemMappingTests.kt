package com.example.dummyapp.presentation.models.userDetails

import com.example.dummyapp.utils.fixtures.entities.DomainFixtures
import com.example.dummyapp.utils.fixtures.entities.PresenterFixtures
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UIUserDetailsItemMappingTests {
    
    @Test
    fun testToUIItem() {
        val expectedDomain = DomainFixtures.DomainUserDetailsUtils.create()
        val expectedUIItem = PresenterFixtures.UIUserDetailsItemUtils.create()
        assertThat(expectedDomain.toUIItem(), equalTo(expectedUIItem))
    }
}