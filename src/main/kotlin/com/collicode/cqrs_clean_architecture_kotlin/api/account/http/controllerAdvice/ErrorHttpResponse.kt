package com.collicode.cqrs_clean_architecture_kotlin.api.account.http.controllerAdvice

data class ErrorHttpResponse(
    val status: Int,
    val message: String,
    val timestamp: String
)
