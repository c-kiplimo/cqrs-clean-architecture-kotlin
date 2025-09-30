package com.collicode.cqrs_clean_architecture_kotlin.application.common.clients

import arrow.core.Either
import com.collicode.common.exception.errors.AppError

interface PaymentClient {
    suspend fun verifyPaymentTransaction(accountId: String, transactionId: String): Either<AppError, Unit>
}