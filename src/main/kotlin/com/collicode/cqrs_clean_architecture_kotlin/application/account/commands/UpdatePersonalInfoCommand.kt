package com.collicode.cqrs_clean_architecture_kotlin.application.account.commands

import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.AccountId
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.PersonalInfo

data class UpdatePersonalInfoCommand(
    val accountId: AccountId,
    val personalInfo: PersonalInfo
) : AccountCommand {
    companion object
}
