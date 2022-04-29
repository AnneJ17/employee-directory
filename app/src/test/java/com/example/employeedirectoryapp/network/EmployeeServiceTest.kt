package com.example.employeedirectoryapp.network

import com.example.employeedirectoryapp.MainCoroutineRule
import com.example.employeedirectoryapp.model.NetworkResult
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class EmployeeServiceTest : MockServer<EmployeeService>() {

    private lateinit var service: EmployeeService

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinRule = MainCoroutineRule()

    @Before
    fun initService() {
        service = createService(EmployeeService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun fetchEmployeeListFromNetworkTest() = runBlocking {
        enqueueResponse("/Employees.json")
        val response = service.fetchEmployees()
        val responseBody = requireNotNull((response as NetworkResult.Success).data)
        mockServer.takeRequest()

        assertEquals(responseBody.employees.size, 11)
        assertEquals(responseBody.employees[0].full_name, "Justine Mason")
        assertEquals(responseBody.employees[0].team, "Point of Sale")
        val url = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg"
        assertEquals(responseBody.employees[0].photo_url_small, url)
    }
}