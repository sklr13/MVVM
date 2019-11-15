package com.example.network.error

class AuthorizationException(val statusCode: Int, s: String) : RuntimeException(s)