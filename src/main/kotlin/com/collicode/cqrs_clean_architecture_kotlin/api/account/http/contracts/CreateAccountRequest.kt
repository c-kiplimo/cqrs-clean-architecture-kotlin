package com.collicode.cqrs_clean_architecture_kotlin.api.account.http.contracts

import com.collicode.cqrs_clean_architecture_kotlin.api.account.http.contracts.dto.*
import com.collicode.cqrs_clean_architecture_kotlin.application.account.commands.CreateAccountCommand
import jakarta.validation.Valid

data class CreateAccountRequest(
    @field:Valid val contactInfo: ContactInfoRequest,
    @field:Valid val personalInfo: PersonalInfoRequest,
    @field:Valid val address: AddressRequest,
) {
    companion object
}

fun CreateAccountRequest.toCommand() = CreateAccountCommand(
    contactInfo = contactInfo.toContactInfo(),
    personalInfo = personalInfo.toPersonalInfo(),
    address = address.toAddress(),
)