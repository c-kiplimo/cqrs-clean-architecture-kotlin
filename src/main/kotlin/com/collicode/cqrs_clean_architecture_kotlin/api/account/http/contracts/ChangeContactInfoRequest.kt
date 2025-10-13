package com.collicode.cqrs_clean_architecture_kotlin.api.account.http.contracts

import com.collicode.cqrs_clean_architecture_kotlin.api.account.http.contracts.dto.ContactInfoRequest
import com.collicode.cqrs_clean_architecture_kotlin.api.account.http.contracts.dto.toContactInfo
import com.collicode.cqrs_clean_architecture_kotlin.application.account.commands.ChangeContactInfoCommand
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.AccountId

import jakarta.validation.Valid

data class ChangeContactInfoRequest(@field:Valid val contactInfo: ContactInfoRequest) {
    companion object
}

fun ChangeContactInfoRequest.toCommand(accountId: AccountId) = ChangeContactInfoCommand(
    accountId = accountId,
    contactInfo = contactInfo.toContactInfo()
)