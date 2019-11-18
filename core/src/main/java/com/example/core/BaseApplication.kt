package com.example.core

import com.example.core.di.AppComponentContract

interface BaseApplication {

    fun getComponent(): AppComponentContract
}