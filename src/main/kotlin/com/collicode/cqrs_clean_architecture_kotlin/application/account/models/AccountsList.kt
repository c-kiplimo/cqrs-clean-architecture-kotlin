package com.collicode.cqrs_clean_architecture_kotlin.application.account.models

import com.collicode.cqrs_clean_architecture_kotlin.domain.account.Account

data class AccountsList(
    val page: Int,
    val size: Int,
    val totalCount: Int,
    val accountsList: List<Account>
) {
    companion object
}
