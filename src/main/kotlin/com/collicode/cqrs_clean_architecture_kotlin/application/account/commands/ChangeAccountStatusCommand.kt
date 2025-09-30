package com.collicode.cqrs_clean_architecture_kotlin.application.account.commands

import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.AccountId
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.AccountStatus

data class ChangeAccountStatusCommand(val accountId: AccountId, val status: AccountStatus) : AccountCommand {
    companion object
}
