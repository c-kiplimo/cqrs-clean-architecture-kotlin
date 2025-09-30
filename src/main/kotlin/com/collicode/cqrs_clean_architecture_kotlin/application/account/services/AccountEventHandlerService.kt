package com.collicode.cqrs_clean_architecture_kotlin.application.account.services

import arrow.core.Either
import com.collicode.common.exception.errors.AppError
import com.collicode.cqrs_clean_architecture_kotlin.application.account.events.*

interface AccountEventHandlerService {

    suspend fun on(event: AccountCreatedEvent): Either<AppError, Unit>

    suspend fun on(event: BalanceDepositedEvent): Either<AppError, Unit>

    suspend fun on(event: BalanceWithdrawEvent): Either<AppError, Unit>

    suspend fun on(event: PersonalInfoUpdatedEvent): Either<AppError, Unit>

    suspend fun on(event: ContactInfoChangedEvent): Either<AppError, Unit>

    suspend fun on(event: AccountStatusChangedEvent): Either<AppError, Unit>
}