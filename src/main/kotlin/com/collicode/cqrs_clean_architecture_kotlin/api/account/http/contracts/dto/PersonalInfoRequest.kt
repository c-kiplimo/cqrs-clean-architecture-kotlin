package com.collicode.cqrs_clean_architecture_kotlin.api.account.http.contracts.dto

import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.PersonalInfo
import jakarta.validation.constraints.Size

data class PersonalInfoRequest(
    @field:Size(max = 10_000, min = 6) val bio: String,
    @field:Size(max = 500, min = 6) val imageUrl: String
) {
    companion object
}


fun PersonalInfoRequest.toPersonalInfo() = PersonalInfo(
    bio = bio,
    imageUrl = imageUrl
)