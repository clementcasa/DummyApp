package com.example.dummyapp.presentation.models.userTitle

import com.example.dummyapp.domain.models.DomainUserGender
import com.example.dummyapp.domain.models.DomainUserTitle
import com.example.dummyapp.presentation.models.userGender.UIUserGenderItem
import com.example.dummyapp.presentation.models.userGender.toUIItem
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UIUserTitleItemMappingTests {
    
    @Test
    fun testToUIItem() {
        DomainUserTitle.values().forEach {
            when(it) {
                DomainUserTitle.MR -> assertThat(it.toUIItem(), equalTo(UIUserTitleItem.MR))
                DomainUserTitle.MS -> assertThat(it.toUIItem(), equalTo(UIUserTitleItem.MS))
                DomainUserTitle.MISS -> assertThat(it.toUIItem(), equalTo(UIUserTitleItem.MISS))
                DomainUserTitle.MRS -> assertThat(it.toUIItem(), equalTo(UIUserTitleItem.MRS))
                DomainUserTitle.DR -> assertThat(it.toUIItem(), equalTo(UIUserTitleItem.DR))
                DomainUserTitle.NONE -> assertThat(it.toUIItem(), equalTo(UIUserTitleItem.NONE))
                else -> Assert.fail("Missing test for UIUserTitleItem")
            }
        }
    }
}