package com.collicode.cqrs_clean_architecture_kotlin.application.account.services

import arrow.core.Either
import com.collicode.common.exception.errors.AppError
import com.collicode.common.exception.errors.LowerEventVersionError
import com.collicode.common.exception.errors.SameEventVersionError
import com.collicode.common.exception.errors.UpperEventVersionError
import com.collicode.common.scope.eitherScope
import com.collicode.cqrs_clean_architecture_kotlin.application.account.events.*
import com.collicode.cqrs_clean_architecture_kotlin.application.account.persistance.AccountProjectionRepository
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.Account
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.AccountId
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.springframework.stereotype.Component


@Component
class AccountEventHandlerServiceImpl(
    private val accountProjectionRepository: AccountProjectionRepository
) : AccountEventHandlerService {

    override suspend fun on(event: AccountCreatedEvent): Either<AppError, Unit> = eitherScope(ctx) {
        accountProjectionRepository.save(event.toAccount()).bind()
    }

    override suspend fun on(event: BalanceDepositedEvent): Either<AppError, Unit> = eitherScope(ctx) {
        findAndUpdateAccountById(event.accountId, event.version) { account ->
            account.depositBalance(event.balance).bind()
        }.bind()
    }

    override suspend fun on(event: BalanceWithdrawEvent): Either<AppError, Unit> = eitherScope(ctx) {
        findAndUpdateAccountById(event.accountId, event.version) { account ->
            account.withdrawBalance(event.balance).bind()
        }.bind()
    }

    override suspend fun on(event: PersonalInfoUpdatedEvent): Either<AppError, Unit> = eitherScope(ctx) {
        findAndUpdateAccountById(event.accountId, event.version) { account ->
            account.changePersonalInfo(event.personalInfo).bind()
        }.bind()
    }

    override suspend fun on(event: ContactInfoChangedEvent): Either<AppError, Unit> = eitherScope(ctx) {
        findAndUpdateAccountById(event.accountId, event.version) { account ->
            account.changeContactInfo(event.contactInfo).bind()
        }.bind()
    }

    override suspend fun on(event: AccountStatusChangedEvent): Either<AppError, Unit> = eitherScope(ctx) {
        findAndUpdateAccountById(event.accountId, event.version) { account ->
            account.updateStatus(event.status).bind()
        }.bind()
    }

    private suspend fun findAndUpdateAccountById(
        accountId: AccountId,
        eventVersion: Long,
        block: suspend (Account) -> Account
    ): Either<AppError, Account> = eitherScope(ctx) {
        val foundAccount = findAndValidateVersion(accountId, eventVersion).bind()
        val accountToUpdate = block(foundAccount)
        accountProjectionRepository.update(accountToUpdate).bind()
    }
        .onRight { log.info { "updated account: $it" } }
        .onLeft { log.error { "error while updating account: $it" } }

    private suspend fun findAndValidateVersion(
        accountId: AccountId,
        eventVersion: Long
    ): Either<AppError, Account> = eitherScope(ctx) {
        val foundAccount = accountProjectionRepository.getById(accountId).bind()
        validateVersion(foundAccount, eventVersion).bind()
        foundAccount
    }

    private val ctx = Job() + CoroutineName(this::class.java.name) + Dispatchers.IO

    private companion object {
        private val log = KotlinLogging.logger { }
    }
}


internal suspend fun validateVersion(account: Account, eventVersion: Long) = eitherScope<AppError, Unit> {
    when {
        eventVersion < account.version + 1 ->
            raise(LowerEventVersionError(account.accountId, account.version, eventVersion))

        eventVersion == account.version ->
            raise(SameEventVersionError(account.accountId, account.version, eventVersion))

        eventVersion > account.version + 1 ->
            raise(UpperEventVersionError(account.accountId, account.version, eventVersion))
    }
}

