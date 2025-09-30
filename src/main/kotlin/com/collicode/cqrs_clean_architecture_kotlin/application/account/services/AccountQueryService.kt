package com.collicode.cqrs_clean_architecture_kotlin.application.account.services

import arrow.core.Either
import com.collicode.common.exception.errors.AppError
import com.collicode.cqrs_clean_architecture_kotlin.application.account.models.AccountsList
import com.collicode.cqrs_clean_architecture_kotlin.application.account.queries.GetAccountByEmailQuery
import com.collicode.cqrs_clean_architecture_kotlin.application.account.queries.GetAccountByIdQuery
import com.collicode.cqrs_clean_architecture_kotlin.application.account.queries.GetAllAccountsQuery
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.Account

interface AccountQueryService {

    suspend fun handle(query: GetAccountByIdQuery): Either<AppError, Account>

    suspend fun handle(query: GetAccountByEmailQuery): Either<AppError, Account>

    suspend fun handle(query: GetAllAccountsQuery): Either<AppError, AccountsList>
}