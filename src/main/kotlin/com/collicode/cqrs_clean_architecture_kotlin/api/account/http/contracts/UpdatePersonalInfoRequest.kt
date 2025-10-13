package com.collicode.cqrs_clean_architecture_kotlin.api.account.http.contracts

import com.collicode.cqrs_clean_architecture_kotlin.api.account.http.contracts.dto.PersonalInfoRequest
import com.collicode.cqrs_clean_architecture_kotlin.api.account.http.contracts.dto.toPersonalInfo
import com.collicode.cqrs_clean_architecture_kotlin.application.account.commands.UpdatePersonalInfoCommand
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.AccountId
import jakarta.validation.Valid

data class UpdatePersonalInfoRequest(@field:Valid val personalInfo: PersonalInfoRequest) {
    companion object
}

fun UpdatePersonalInfoRequest.toCommand(accountId: AccountId) = UpdatePersonalInfoCommand(
    accountId = accountId,
    personalInfo = personalInfo.toPersonalInfo()
)