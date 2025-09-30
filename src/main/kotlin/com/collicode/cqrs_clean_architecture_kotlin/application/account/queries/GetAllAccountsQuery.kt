package com.collicode.cqrs_clean_architecture_kotlin.application.account.queries

data class GetAllAccountsQuery(val page: Int, val size: Int) : AccountQuery
