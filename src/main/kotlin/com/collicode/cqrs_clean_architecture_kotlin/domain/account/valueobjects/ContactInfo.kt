package com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects

data class ContactInfo(
    val email: String = "",
    val phone: String = ""
)
