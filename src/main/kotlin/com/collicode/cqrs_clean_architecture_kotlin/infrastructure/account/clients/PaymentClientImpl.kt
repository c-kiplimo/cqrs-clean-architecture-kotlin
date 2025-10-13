package com.collicode.cqrs_clean_architecture_kotlin.infrastructure.account.clients

import arrow.core.Either
import arrow.core.raise.either
import com.collicode.common.exception.errors.AppError
import com.collicode.common.exception.errors.InvalidTransactionError
import com.collicode.cqrs_clean_architecture_kotlin.application.common.clients.PaymentClient
import org.springframework.stereotype.Component


/*
Example implementation for demonstration purposes
 */
@Component
class PaymentClientImpl : PaymentClient {

    override suspend fun verifyPaymentTransaction(
        accountId: String,
        transactionId: String
    ): Either<AppError, Unit> = either {
        if (accountId.isBlank() || transactionId.isBlank())
            raise(InvalidTransactionError("invalid transaction id: $transactionId, accountId: $accountId"))
    }
}