package com.example.dummyapp.data.utils

import com.example.dummyapp.data.fixtures.FixturesConstants
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StringExtensionTests {
    
    @Test
    fun testToDateSuccess() {
        assertNotNull(FixturesConstants.User.dateOfBirth.toDate())
    }
    
    @Test
    fun testToDateFailure() {
        assertNull("date".toDate())
    }
}