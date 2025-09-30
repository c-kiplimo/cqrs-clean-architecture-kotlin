package com.collicode.cqrs_clean_architecture_kotlin.application.account.persistance

import arrow.core.Either
import com.collicode.cqrs_clean_architecture_kotlin.application.account.models.AccountsList
import com.collicode.common.exception.errors.AppError
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.Account
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.AccountId

interface AccountProjectionRepository {

    suspend fun save(account: Account): Either<AppError, Account>

    suspend fun update(account: Account): Either<AppError, Account>

    suspend fun getById(id: AccountId): Either<AppError, Account>

    suspend fun getByEmail(email: String): Either<AppError, Account>

    suspend fun getAll(page: Int, size: Int): Either<AppError, AccountsList>

    suspend fun upsert(account: Account): Either<AppError, Account>
}