package com.example.employeedirectoryapp.ui.employee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.employeedirectoryapp.inject.DispatcherModule.IoDispatcher
import com.example.employeedirectoryapp.model.Employee
import com.example.employeedirectoryapp.network.EmployeeRepository
import com.example.employeedirectoryapp.util.onError
import com.example.employeedirectoryapp.util.onException
import com.example.employeedirectoryapp.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import logcat.logcat
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel
@Inject constructor(
    private val employeeRepository: EmployeeRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _employeeList = MutableStateFlow<List<Employee>>(emptyList())
    val employeesFlow: Flow<List<Employee>> = _employeeList

    private val _swipeRefreshActive: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val swipeRefreshActive: LiveData<Boolean> = _swipeRefreshActive

    val swipeRefreshListener: SwipeRefreshLayout.OnRefreshListener =
        SwipeRefreshLayout.OnRefreshListener {
            fetchEmployees()
        }

    fun fetchEmployees() = viewModelScope.launch(ioDispatcher) {
        _swipeRefreshActive.postValue(true)
        val response = employeeRepository.fetchEmployees()
        _swipeRefreshActive.postValue(false)

        response.onSuccess { list ->
            _employeeList.value = list.employees
        }.onError { code, message ->
            logcat { "$code, $message" }
        }.onException { t ->
            logcat { "$t" }
        }
    }
}