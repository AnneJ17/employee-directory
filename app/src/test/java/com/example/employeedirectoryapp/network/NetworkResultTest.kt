package com.example.employeedirectoryapp.network

import com.example.employeedirectoryapp.model.NetworkResult
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NetworkResultTest {

    @Test
    fun success() {
        val response = NetworkResult.Success("success")
        assertEquals(response.data, "success")
    }

    @Test
    fun error() {
        val error = Error("error")
        val response = NetworkResult.Error<String>(404, message = error.message!!)
        assertEquals(response.code, 404)
        assertEquals(response.message, "error")
    }
}