package com.example.core.network.error

class AuthorizationException(val statusCode: Int, s: String) : RuntimeException(s)