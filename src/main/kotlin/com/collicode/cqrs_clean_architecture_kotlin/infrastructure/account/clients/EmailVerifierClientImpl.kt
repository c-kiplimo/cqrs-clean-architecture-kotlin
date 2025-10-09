package com.collicode.cqrs_clean_architecture_kotlin.infrastructure.account.clients

import arrow.core.Either
import arrow.core.raise.either
import com.collicode.common.exception.errors.AppError
import com.collicode.common.exception.errors.EmailValidationError
import com.collicode.cqrs_clean_architecture_kotlin.application.common.clients.EmailVerifierClient
import kotlinx.coroutines.delay
import org.springframework.stereotype.Component

@Component
class EmailVerifierClientImpl : EmailVerifierClient {

    override suspend fun verifyEmail(email: String): Either<AppError, Unit> = either {
        delay(300)
        if (email.isBlank()) raise(EmailValidationError("invalid email: $email"))
    }
}