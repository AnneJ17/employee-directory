package com.example.employeedirectoryapp.startup

import android.content.Context
import androidx.startup.Initializer
import coil.Coil
import coil.ImageLoader

class CoilInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        Coil.setImageLoader(
            ImageLoader.Builder(context)
                .build()
        )
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}