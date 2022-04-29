package com.example.employeedirectoryapp.utils

import com.example.employeedirectoryapp.model.Employee

object MockUtil {

    fun mockEmployee() = Employee(
        uuid = "0d8fcc12-4d0c-425c-8355-390b312b909c",
        full_name = "Justine Mason",
        photo_url_small = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
        email_address = "jmason.demo@squareup.com",
        team = "Point of Sale",
        employee_type = "FULL_TIME",
    )

    fun mockEmployeeList() = listOf(mockEmployee())
}
