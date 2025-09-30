package com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects


data class PersonalInfo(val bio: String = "", val imageUrl: String = "") {
    companion object
}