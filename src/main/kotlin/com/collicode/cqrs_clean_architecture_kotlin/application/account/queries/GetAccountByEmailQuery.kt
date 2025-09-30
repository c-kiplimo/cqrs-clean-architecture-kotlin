package com.collicode.cqrs_clean_architecture_kotlin.application.account.queries

import com.collicode.cqrs_clean_architecture_kotlin.application.account.queries.AccountQuery

data class GetAccountByEmailQuery(val email: String) : AccountQuery
