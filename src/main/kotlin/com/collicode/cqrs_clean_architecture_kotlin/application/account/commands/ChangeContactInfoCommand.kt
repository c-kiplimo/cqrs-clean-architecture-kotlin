package com.collicode.cqrs_clean_architecture_kotlin.application.account.commands

import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.AccountId
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.ContactInfo

data class ChangeContactInfoCommand(val accountId: AccountId, val contactInfo: ContactInfo) : AccountCommand {
    companion object
}
