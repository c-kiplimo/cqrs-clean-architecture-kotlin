package com.collicode.cqrs_clean_architecture_kotlin.application.account.events

import java.time.Instant

sealed interface DomainEvent {
    val aggregateId: String
    val version: Long
    val eventId: String
    val eventType: String
    val timestamp: Instant
}