package com.example.dummyapp.utils.fixtures.exceptions

import com.example.dummyapp.data.exceptions.DataAPIDecodeException
import com.example.dummyapp.data.exceptions.DataNetworkException

class DataExceptionsFixtures {
    
    //region DataNetworkException
    object DataNetworkExceptionUtils {
        fun create(statusCode: Int, message: String = "message"): DataNetworkException =
            DataNetworkException(statusCode, message)
    }
    //endregion
    
    //region DataAPIException
    object DataAPIExceptionUtils {
        fun create(message: String): DataAPIDecodeException =
            DataAPIDecodeException(message)
    }
    //endregion
}