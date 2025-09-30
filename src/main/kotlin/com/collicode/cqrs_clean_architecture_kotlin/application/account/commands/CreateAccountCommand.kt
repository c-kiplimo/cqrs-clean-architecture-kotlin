package com.collicode.cqrs_clean_architecture_kotlin.application.account.commands

import com.collicode.cqrs_clean_architecture_kotlin.domain.account.Account
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.AccountId
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.Address
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.ContactInfo
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.PersonalInfo
import java.time.Instant
import java.util.*

data class CreateAccountCommand(
    val contactInfo: ContactInfo = ContactInfo(),
    val personalInfo: PersonalInfo = PersonalInfo(),
    val address: Address = Address(),
) : AccountCommand {
    companion object
}

fun CreateAccountCommand.toAccount() = Account(
    accountId = AccountId(id = UUID.randomUUID()),
    contactInfo = contactInfo,
    address = address,
    personalInfo = personalInfo,
    updatedAt = Instant.now(),
    createdAt = Instant.now(),
    version = 1
)