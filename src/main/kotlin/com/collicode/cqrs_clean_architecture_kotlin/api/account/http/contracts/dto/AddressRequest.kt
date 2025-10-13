package com.collicode.cqrs_clean_architecture_kotlin.api.account.http.contracts.dto

import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.Address
import jakarta.validation.constraints.Size

data class AddressRequest(
    @field:Size(min = 6, max = 60) val country: String? = null,
    @field:Size(min = 6, max = 60) val city: String? = null,
    @field:Size(max = 20) val postCode: String? = null
) {
    companion object
}

fun AddressRequest.toAddress() = Address(
    country = country,
    city = city,
    postCode = postCode
)