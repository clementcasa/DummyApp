package com.example.dummyapp.presentation.models.userPreview

import com.example.dummyapp.utils.fixtures.entities.DomainFixtures
import com.example.dummyapp.utils.fixtures.entities.PresenterFixtures
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UIUserPreviewItemMappingTests {
    
    @Test
    fun testToUIItem() {
        val expectedDomain = DomainFixtures.DomainUserPreviewUtils.create()
        val expectedUIItem = PresenterFixtures.UIUserPreviewItemUtils.create()
        assertThat(expectedDomain.toUIItem(), equalTo(expectedUIItem))
    }
}