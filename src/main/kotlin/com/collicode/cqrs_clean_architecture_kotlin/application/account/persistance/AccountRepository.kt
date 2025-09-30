package com.collicode.cqrs_clean_architecture_kotlin.application.account.persistance

import arrow.core.Either
import com.collicode.common.exception.errors.AppError
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.Account
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.AccountId

interface AccountRepository {

    suspend fun getById(id: AccountId): Either<AppError, Account>

    suspend fun save(account: Account): Either<AppError, Account>

    suspend fun update(account: Account): Either<AppError, Account>
}