package com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects

import java.util.*

@JvmInline
value class AccountId(val id: UUID? = null) {
    fun string() = id?.toString() ?: ""
}