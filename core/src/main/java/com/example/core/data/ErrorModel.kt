package com.example.core.data

data class ErrorModel(val throwable: Throwable?, val runnable: () -> Unit)