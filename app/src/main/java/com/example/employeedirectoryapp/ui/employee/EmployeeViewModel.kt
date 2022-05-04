package com.example.employeedirectoryapp.ui.employee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.employeedirectoryapp.inject.DispatcherModule.IoDispatcher
import com.example.employeedirectoryapp.model.EmployeeGroupedItem
import com.example.employeedirectoryapp.network.EmployeeRepository
import com.example.employeedirectoryapp.util.onError
import com.example.employeedirectoryapp.util.onException
import com.example.employeedirectoryapp.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel
@Inject constructor(
    private val employeeRepository: EmployeeRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _employeeList = MutableStateFlow<List<EmployeeGroupedItem.Employee>>(emptyList())
    val employeesFlow: Flow<List<EmployeeGroupedItem.Employee>> = _employeeList

    private val _error = MutableSharedFlow<String>()
    val errorFlow: Flow<String> = _error

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
            _error.emit("$code, $message")
        }.onException { t ->
            _error.emit("${t.message}")
        }
    }

    fun fetchEmployeeGroupedByTeam(employees: List<EmployeeGroupedItem.Employee>): List<EmployeeGroupedItem> {
        val groupedItems = mutableListOf<EmployeeGroupedItem>()
        val groupedEmployees = employees
            .groupBy { it.team }
            .mapKeys { entry ->
                employees.find { employees -> employees.team == entry.key }
            }
        groupedEmployees.forEach { (item, employees) ->
            groupedItems.add(EmployeeGroupedItem.Header(item?.team))
            employees.forEach { employee ->
                groupedItems.add(
                    EmployeeGroupedItem.Employee(
                        uuid = employee.uuid,
                        full_name = employee.full_name,
                        email_address = employee.email_address,
                        team = employee.team,
                        employee_type = employee.employee_type,
                        biography = employee.biography,
                        photo_url_small = employee.photo_url_small,
                        photo_url_large = employee.photo_url_large,
                        phone_number = employee.phone_number
                    )
                )
            }
        }
        return groupedItems
    }
}