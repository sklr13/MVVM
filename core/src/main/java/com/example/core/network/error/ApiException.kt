package com.example.core.network.error

class ApiException(val errorInfo: ErrorInfo) : RuntimeException()