package com.collicode.cqrs_clean_architecture_kotlin.application.common.clients

import arrow.core.Either
import com.collicode.common.exception.errors.AppError

interface EmailVerifierClient {
    suspend fun verifyEmail(email: String): Either<AppError, Unit>
}