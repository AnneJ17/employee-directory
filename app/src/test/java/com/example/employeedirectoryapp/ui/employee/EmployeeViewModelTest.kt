package com.example.employeedirectoryapp.ui.employee

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.employeedirectoryapp.MainCoroutineRule
import com.example.employeedirectoryapp.model.EmployeeResponse
import com.example.employeedirectoryapp.model.NetworkResult
import com.example.employeedirectoryapp.network.EmployeeRepository
import com.example.employeedirectoryapp.network.EmployeeService
import com.example.employeedirectoryapp.utils.MockUtil.mockEmployee
import com.example.employeedirectoryapp.utils.MockUtil.mockEmployeeList
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.validateMockitoUsage
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@ExperimentalTime
@ExperimentalCoroutinesApi
class EmployeeViewModelTest {

    private lateinit var viewModel: EmployeeViewModel
    private lateinit var repo: EmployeeRepository
    private val service: EmployeeService = mock()

    @get:Rule
    val task = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        repo = EmployeeRepository(service)
        viewModel = EmployeeViewModel(repo, coroutineRule.testDispatcher)
    }

    @After
    fun validate() {
        validateMockitoUsage() // Ensure error appears in the correct test method
    }

    @Test
    fun `fetch employees from network`() = runTest {
        val mockData = mockEmployeeList()
        `when`(repo.fetchEmployees()).thenReturn(NetworkResult.Success(EmployeeResponse(mockData)))
        viewModel.fetchEmployees()
        viewModel.employeesFlow.test(2.toDuration(DurationUnit.SECONDS)) {
            val expectItem = awaitItem()
            assertEquals(expectItem[0].full_name, mockEmployee().full_name)
            assertEquals(expectItem[0].team, mockEmployee().team)
            assertEquals(expectItem, mockData)
        }

    }
}