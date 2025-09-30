package com.collicode.cqrs_clean_architecture_kotlin.application.outbox.persitence

import arrow.core.Either
import com.collicode.common.exception.errors.AppError
import com.collicode.cqrs_clean_architecture_kotlin.domain.outbox.OutboxEvent

interface OutboxRepository {

    suspend fun insert(event: OutboxEvent): Either<AppError, OutboxEvent>

    suspend fun deleteWithLock(
        event: OutboxEvent,
        callback: suspend (event: OutboxEvent) -> Either<AppError, Unit>
    ): Either<AppError, OutboxEvent>

    suspend fun deleteEventsWithLock(
        batchSize: Int,
        callback: suspend (event: OutboxEvent) -> Either<AppError, Unit>
    ): Either<AppError, Unit>
}