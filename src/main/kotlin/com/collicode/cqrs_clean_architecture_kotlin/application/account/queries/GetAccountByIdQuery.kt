package com.collicode.cqrs_clean_architecture_kotlin.application.account.queries

import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.AccountId

data class GetAccountByIdQuery(val id: AccountId) : AccountQuery
