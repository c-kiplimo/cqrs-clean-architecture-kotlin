package com.collicode.cqrs_clean_architecture_kotlin.api.account.http.contracts

import com.collicode.cqrs_clean_architecture_kotlin.application.account.commands.ChangeAccountStatusCommand
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.AccountId
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.AccountStatus


data class ChangeAccountStatusRequest(val status: AccountStatus) {
    companion object
}

fun ChangeAccountStatusRequest.toCommand(accountId: AccountId) = ChangeAccountStatusCommand(
    accountId = accountId,
    status = this.status,
)