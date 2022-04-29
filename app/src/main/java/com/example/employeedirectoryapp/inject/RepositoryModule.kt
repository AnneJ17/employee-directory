package com.example.employeedirectoryapp.inject

import com.example.employeedirectoryapp.network.EmployeeRepository
import com.example.employeedirectoryapp.network.EmployeeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideEmployeeRepository(
        employeeService: EmployeeService
    ): EmployeeRepository {
        return EmployeeRepository(employeeService)
    }
}