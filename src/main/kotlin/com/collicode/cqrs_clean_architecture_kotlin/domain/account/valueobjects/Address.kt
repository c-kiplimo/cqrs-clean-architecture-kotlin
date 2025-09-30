package com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects

data class Address(
    val country: String? = null,
    val city: String? = null,
    val postCode: String? = null
)
