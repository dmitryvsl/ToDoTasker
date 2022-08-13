package com.example.todotasker

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class App : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var context: Context

        fun getContext(): Context = context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }


}