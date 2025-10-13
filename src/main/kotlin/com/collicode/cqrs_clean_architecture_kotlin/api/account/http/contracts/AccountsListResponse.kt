package com.collicode.cqrs_clean_architecture_kotlin.api.account.http.contracts

import com.collicode.cqrs_clean_architecture_kotlin.application.account.models.AccountsList


data class AccountsListResponse(
    val page: Int,
    val size: Int,
    val totalCount: Int,
    val accountsList: List<AccountResponse>
) {
    companion object
}

fun AccountsList.toHttpResponse() = AccountsListResponse(
    page = page,
    size = size,
    totalCount = totalCount,
    accountsList = accountsList.map { it.toResponse() }
)
