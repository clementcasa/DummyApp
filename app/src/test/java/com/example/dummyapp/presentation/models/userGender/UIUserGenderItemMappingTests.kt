package com.example.dummyapp.presentation.models.userGender

import com.example.dummyapp.domain.models.DomainUserGender
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UIUserGenderItemMappingTests {
    
    @Test
    fun testToUIItem() {
        DomainUserGender.values().forEach {
            when(it) {
                DomainUserGender.MALE -> assertThat(it.toUIItem(), equalTo(UIUserGenderItem.MALE))
                DomainUserGender.FEMALE -> assertThat(it.toUIItem(), equalTo(UIUserGenderItem.FEMALE))
                DomainUserGender.OTHER -> assertThat(it.toUIItem(), equalTo(UIUserGenderItem.OTHER))
                DomainUserGender.NONE -> assertThat(it.toUIItem(), equalTo(UIUserGenderItem.NONE))
                else -> fail("Missing test for UIUserGenderItem")
            }
        }
    }
}