package com.example.employeedirectoryapp.network

import com.example.employeedirectoryapp.model.EmployeeResponse
import com.example.employeedirectoryapp.model.NetworkResult
import retrofit2.http.GET

interface EmployeeService {

    @GET("employees.json")
    suspend fun fetchEmployees(): NetworkResult<EmployeeResponse>
}