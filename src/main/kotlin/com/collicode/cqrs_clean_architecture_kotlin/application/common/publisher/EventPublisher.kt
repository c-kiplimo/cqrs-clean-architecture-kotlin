package com.collicode.cqrs_clean_architecture_kotlin.application.common.publisher

import arrow.core.Either
import com.collicode.common.exception.errors.AppError
import com.collicode.cqrs_clean_architecture_kotlin.domain.outbox.OutboxEvent

interface EventPublisher {

    suspend fun publish(event: OutboxEvent, headers: Map<String, ByteArray> = mapOf()): Either<AppError, Unit>

    suspend fun publish(events: List<OutboxEvent>): Either<AppError, Unit>

    suspend fun publish(topic: String, data: Any, headers: Map<String, ByteArray> = mapOf()): Either<AppError, Unit>

    suspend fun publish(
        topic: String,
        key: String,
        data: Any,
        headers: Map<String, ByteArray> = mapOf()
    ): Either<AppError, Unit>

    suspend fun publishBytes(
        topic: String,
        key: String,
        data: ByteArray,
        headers: Map<String, ByteArray> = mapOf()
    ): Either<AppError, Unit>
}