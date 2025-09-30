package com.collicode.cqrs_clean_architecture_kotlin.application.account.services

import arrow.core.Either
import com.collicode.common.exception.errors.AppError
import com.collicode.cqrs_clean_architecture_kotlin.application.account.commands.*
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.AccountId

interface AccountCommandService {

    suspend fun handle(command: CreateAccountCommand): Either<AppError, AccountId>

    suspend fun handle(command: ChangeAccountStatusCommand): Either<AppError, Unit>

    suspend fun handle(command: ChangeContactInfoCommand): Either<AppError, Unit>

    suspend fun handle(command: DepositBalanceCommand): Either<AppError, Unit>

    suspend fun handle(command: WithdrawBalanceCommand): Either<AppError, Unit>

    suspend fun handle(command: UpdatePersonalInfoCommand): Either<AppError, Unit>
}