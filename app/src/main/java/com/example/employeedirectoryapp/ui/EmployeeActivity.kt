package com.example.employeedirectoryapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.employeedirectoryapp.R
import com.example.employeedirectoryapp.databinding.ActivityEmployeeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_employee)
    }
}