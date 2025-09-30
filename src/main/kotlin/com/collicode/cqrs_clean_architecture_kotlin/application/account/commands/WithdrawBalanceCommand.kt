package com.collicode.cqrs_clean_architecture_kotlin.application.account.commands


import com.collicode.cqrs_clean_architecture_kotlin.application.account.commands.AccountCommand
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.AccountId
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.Balance

data class WithdrawBalanceCommand(
    val accountId: AccountId,
    val balance: Balance,
    val transactionId: String
) : AccountCommand {
    companion object
}
