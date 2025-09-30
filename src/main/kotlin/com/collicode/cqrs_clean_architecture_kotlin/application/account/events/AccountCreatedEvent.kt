package com.collicode.cqrs_clean_architecture_kotlin.application.account.events

import com.collicode.common.serializer.Serializer
import com.collicode.cqrs_clean_architecture_kotlin.application.account.events.AccountCreatedEvent.Companion.ACCOUNT_CREATED_EVENT_V1
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.Account
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.AccountId
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.Address
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.ContactInfo
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.PersonalInfo
import com.collicode.cqrs_clean_architecture_kotlin.domain.outbox.OutboxEvent
import java.time.Instant
import java.util.*

data class AccountCreatedEvent(
    val accountId: AccountId,
    val contactInfo: ContactInfo = ContactInfo(),
    val personalInfo: PersonalInfo = PersonalInfo(),
    val address: Address = Address(),
    val updatedAt: Instant? = null,
    val createdAt: Instant? = null,

    override val eventId: String,
    override val eventType: String,
    override val aggregateId: String,
    override val version: Long = 0,
    override val timestamp: Instant,
) : DomainEvent {
    companion object {
        const val ACCOUNT_CREATED_EVENT_V1 = "ACCOUNT_CREATED_EVENT_V1"
    }
}

fun AccountCreatedEvent.toOutboxEvent(serializer: Serializer) = OutboxEvent(
    eventId = UUID.randomUUID(),
    eventType = ACCOUNT_CREATED_EVENT_V1,
    aggregateId = aggregateId,
    version = version,
    timestamp = Instant.now(),
    data = serializer.serializeToBytes(this)
)

fun AccountCreatedEvent.toAccount() = Account(
    accountId = AccountId(aggregateId.toUUID()),
    contactInfo = contactInfo,
    personalInfo = personalInfo,
    address = address,
    version = version,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Account.toAccountCreatedEvent(): AccountCreatedEvent {
    return AccountCreatedEvent(
        accountId = accountId,
        aggregateId = accountId.id.toString(),
        contactInfo = contactInfo,
        personalInfo = personalInfo,
        address = address,
        version = version,
        updatedAt = updatedAt,
        createdAt = createdAt,

        eventId = UUID.randomUUID().toString(),
        eventType = ACCOUNT_CREATED_EVENT_V1,
        timestamp = Instant.now()
    )
}


fun Account.toAccountCreatedOutboxEvent(serializer: Serializer): OutboxEvent {
    return this.toAccountCreatedEvent().toOutboxEvent(serializer)
}