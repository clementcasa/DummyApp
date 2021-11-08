package com.example.dummyapp.data.exceptions

import com.example.dummyapp.domain.exception.DomainNetworkException
import com.example.dummyapp.utils.fixtures.exceptions.DataExceptionsFixtures
import com.example.dummyapp.utils.fixtures.exceptions.DomainExceptionsFixtures
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataExceptionsTests {
    
    private val expectedMessage = "message"
    
    @Test
    fun testDataAPIDecodeExceptionToDomain() {
        val expectedDataEmptyBodyException = DataExceptionsFixtures.DataAPIExceptionUtils.create(expectedMessage)
        val expectedDomainException = DomainExceptionsFixtures.DomainNetworkUtils.createInternalError(expectedMessage)
        assertThat(expectedDataEmptyBodyException.toDomain() as DomainNetworkException, equalTo(expectedDomainException))
    }
    
    @Test
    fun testBadRequestErrorToDomain() {
        val expectedDataNetworkException = DataExceptionsFixtures.DataNetworkExceptionUtils.create(400, expectedMessage)
        val expectedDomainNetworkException = DomainExceptionsFixtures.DomainNetworkUtils.createBadRequest(expectedMessage)
        assertThat(expectedDataNetworkException.toDomain() as DomainNetworkException, equalTo(expectedDomainNetworkException))
    }
    
    @Test
    fun testUnauthorizedErrorToDomain() {
        val expectedDataNetworkException = DataExceptionsFixtures.DataNetworkExceptionUtils.create(401, expectedMessage)
        val expectedDomainNetworkException = DomainExceptionsFixtures.DomainNetworkUtils.createUnauthorized(expectedMessage)
        assertThat(expectedDataNetworkException.toDomain() as DomainNetworkException, equalTo(expectedDomainNetworkException))
    }
    
    @Test
    fun testForbiddenErrorToDomain() {
        val expectedDataNetworkException = DataExceptionsFixtures.DataNetworkExceptionUtils.create(403, expectedMessage)
        val expectedDomainNetworkException = DomainExceptionsFixtures.DomainNetworkUtils.createForbidden(expectedMessage)
        assertThat(expectedDataNetworkException.toDomain() as DomainNetworkException, equalTo(expectedDomainNetworkException))
    }
    
    @Test
    fun testNotFoundErrorToDomain() {
        val expectedDataNetworkException = DataExceptionsFixtures.DataNetworkExceptionUtils.create(404, expectedMessage)
        val expectedDomainNetworkException = DomainExceptionsFixtures.DomainNetworkUtils.createNotFound(expectedMessage)
        assertThat(expectedDataNetworkException.toDomain() as DomainNetworkException, equalTo(expectedDomainNetworkException))
    }
    
    @Test
    fun testConflictErrorToDomain() {
        val expectedDataNetworkException = DataExceptionsFixtures.DataNetworkExceptionUtils.create(409, expectedMessage)
        val expectedDomainNetworkException = DomainExceptionsFixtures.DomainNetworkUtils.createConflict(expectedMessage)
        assertThat(expectedDataNetworkException.toDomain() as DomainNetworkException, equalTo(expectedDomainNetworkException))
    }
    
    @Test
    fun testInternalErrorToDomain() {
        val expectedDataNetworkException = DataExceptionsFixtures.DataNetworkExceptionUtils.create(500, expectedMessage)
        val expectedDomainNetworkException = DomainExceptionsFixtures.DomainNetworkUtils.createInternalError(expectedMessage)
        assertThat(expectedDataNetworkException.toDomain() as DomainNetworkException, equalTo(expectedDomainNetworkException))
    }
}