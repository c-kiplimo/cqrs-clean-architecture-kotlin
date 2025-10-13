package com.collicode.cqrs_clean_architecture_kotlin.api.account.http.contracts

import com.collicode.cqrs_clean_architecture_kotlin.domain.account.Account
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.AccountStatus
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.BalanceCurrency


data class AccountResponse(
    val accountId: String,
    val email: String = "",
    val phone: String = "",
    val bio: String = "",
    val imageUrl: String = "",
    val country: String? = null,
    val city: String? = null,
    val postCode: String? = null,
    val amount: Long = 0,
    val currency: BalanceCurrency = BalanceCurrency.USD,
    val balance: String,
    val status: AccountStatus = AccountStatus.FREE
) {
    companion object
}

fun Account.toResponse() = AccountResponse(
    accountId = accountId.string(),
    email = contactInfo.email,
    phone = contactInfo.phone,
    bio = personalInfo.bio,
    imageUrl = personalInfo.imageUrl,
    country = address.country,
    city = address.city,
    postCode = address.postCode,
    amount = balance.amount,
    currency = balance.balanceCurrency,
    balance = "${balance.amount.toBalanceString()} ${balance.balanceCurrency}",
    status = status
)

fun Long.toBalanceString(): String {
    if (this < 100) return "0.$this"
    val str = this.toString()
    val cents = str.substring(str.lastIndex - 1..str.lastIndex)
    val dollars = str.substring(0..<str.lastIndex)
    return "${dollars}.$cents"
}