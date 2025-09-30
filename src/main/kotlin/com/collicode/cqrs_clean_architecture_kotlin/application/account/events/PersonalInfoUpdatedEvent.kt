package com.collicode.cqrs_clean_architecture_kotlin.application.account.events

import com.collicode.common.serializer.Serializer
import com.collicode.cqrs_clean_architecture_kotlin.application.account.events.PersonalInfoUpdatedEvent.Companion.ACCOUNT_PERSONAL_INFO_UPDATED_V1
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.Account
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.AccountId
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.PersonalInfo
import com.collicode.cqrs_clean_architecture_kotlin.domain.outbox.OutboxEvent
import java.time.Instant
import java.util.*

data class PersonalInfoUpdatedEvent(
    val accountId: AccountId,
    val personalInfo: PersonalInfo = PersonalInfo(),
    val updatedAt: Instant? = null,
    val createdAt: Instant? = null,

    override val eventId: String,
    override val version: Long = 0,
    override val aggregateId: String,
    override val eventType: String,
    override val timestamp: Instant,
) : DomainEvent {
    companion object {
        const val ACCOUNT_PERSONAL_INFO_UPDATED_V1 = "ACCOUNT_PERSONAL_INFO_UPDATED_V1"
    }
}

fun Account.toPersonalInfoUpdatedEvent(): PersonalInfoUpdatedEvent {
    return PersonalInfoUpdatedEvent(
        accountId = accountId,
        aggregateId = accountId.id.toString(),
        eventId = UUID.randomUUID().toString(),
        eventType = ACCOUNT_PERSONAL_INFO_UPDATED_V1,
        timestamp = Instant.now(),
        personalInfo = personalInfo,
        version = version,
        updatedAt = updatedAt,
        createdAt = createdAt,
    )
}


fun Account.toPersonalInfoUpdatedOutboxEvent(serializer: Serializer): OutboxEvent {
    return this.toPersonalInfoUpdatedEvent().toOutboxEvent(serializer)
}


fun PersonalInfoUpdatedEvent.toOutboxEvent(serializer: Serializer) = OutboxEvent(
    eventId = UUID.randomUUID(),
    eventType = ACCOUNT_PERSONAL_INFO_UPDATED_V1,
    aggregateId = aggregateId,
    version = version,
    timestamp = Instant.now(),
    data = serializer.serializeToBytes(this)
)