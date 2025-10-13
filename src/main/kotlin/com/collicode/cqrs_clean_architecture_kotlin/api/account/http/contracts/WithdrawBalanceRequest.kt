package com.collicode.cqrs_clean_architecture_kotlin.api.account.http.contracts


import com.collicode.cqrs_clean_architecture_kotlin.api.account.http.contracts.dto.BalanceRequest
import com.collicode.cqrs_clean_architecture_kotlin.api.account.http.contracts.dto.toBalance
import com.collicode.cqrs_clean_architecture_kotlin.application.account.commands.WithdrawBalanceCommand
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.AccountId
import jakarta.validation.Valid
import jakarta.validation.constraints.Size

data class WithdrawBalanceRequest(
    @field:Valid val balance: BalanceRequest,
    @field:Size(min = 6, max = 255) val transactionId: String
) {
    companion object
}

fun WithdrawBalanceRequest.toCommand(accountId: AccountId) = WithdrawBalanceCommand(
    accountId = accountId,
    balance = this.balance.toBalance(),
    transactionId = this.transactionId
)