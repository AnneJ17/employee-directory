package com.example.employeedirectoryapp.network

import com.example.employeedirectoryapp.model.EmployeeResponse
import com.example.employeedirectoryapp.model.NetworkResult
import javax.inject.Inject

class EmployeeRepository
@Inject constructor(
    private val employeeService: EmployeeService
) {
    suspend fun fetchEmployees(): NetworkResult<EmployeeResponse> = employeeService.fetchEmployees()
}