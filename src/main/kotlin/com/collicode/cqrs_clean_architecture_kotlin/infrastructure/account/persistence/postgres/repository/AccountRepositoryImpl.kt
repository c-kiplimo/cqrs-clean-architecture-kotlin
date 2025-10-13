package com.collicode.cqrs_clean_architecture_kotlin.infrastructure.account.persistence.postgres.repository

import arrow.core.Either
import com.collicode.common.exception.errors.AccountNotFoundError
import com.collicode.common.exception.errors.AppError
import com.collicode.common.scope.eitherScope
import com.collicode.cqrs_clean_architecture_kotlin.application.account.persistance.AccountRepository
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.Account
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects.AccountId
import com.collicode.cqrs_clean_architecture_kotlin.infrastructure.account.persistence.postgres.mapper.toAccount
import com.collicode.cqrs_clean_architecture_kotlin.infrastructure.account.persistence.postgres.mapper.toPostgresEntityMap
import com.collicode.cqrs_clean_architecture_kotlin.infrastructure.account.persistence.postgres.sql.GET_ACCOUNT_BY_ID_QUERY
import com.collicode.cqrs_clean_architecture_kotlin.infrastructure.account.persistence.postgres.sql.INSERT_ACCOUNT_QUERY
import com.collicode.cqrs_clean_architecture_kotlin.infrastructure.account.persistence.postgres.sql.OPTIMISTIC_UPDATE_QUERY
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.awaitSingleOrNull
import org.springframework.r2dbc.core.bind
import org.springframework.stereotype.Repository
import java.time.Instant


@Repository
class AccountRepositoryImpl(
    private val dbClient: DatabaseClient
) : AccountRepository {

    override suspend fun save(account: Account): Either<AppError, Account> = eitherScope<AppError, Account>(ctx) {
        dbClient.sql(INSERT_ACCOUNT_QUERY.trimMargin())
            .bindValues(account.withVersion(FIRST_VERSION).toPostgresEntityMap())
            .fetch()
            .rowsUpdated()
            .awaitSingle()
            .also { rowsUpdated -> log.info { "saved account rowsUpdated: $rowsUpdated, id: ${account.accountId}" } }

        account
    }
        .onRight { log.debug { "saved account: $it" } }
        .onLeft { log.error { "error while saving account: $it" } }


    override suspend fun update(account: Account): Either<AppError, Account> = eitherScope(ctx) {
        dbClient.sql(OPTIMISTIC_UPDATE_QUERY.trimMargin())
            .bindValues(account.withUpdatedAt(Instant.now()).toPostgresEntityMap(withOptimisticLock = true))
            .fetch()
            .rowsUpdated()
            .awaitSingle()

        account.incVersion().bind()
    }
        .onRight { log.debug { "updated account: $it" } }
        .onLeft { log.error { "error while update account: $it" } }

    override suspend fun getById(id: AccountId): Either<AppError, Account> = eitherScope(ctx) {
        dbClient.sql(GET_ACCOUNT_BY_ID_QUERY.trimMargin())
            .bind(ID_FIELD, id.id)
            .map { row, _ -> row.toAccount() }
            .awaitSingleOrNull()
            ?: raise(AccountNotFoundError("account for id: $id not found"))
    }
        .onRight { log.debug { "get account by id: $it" } }
        .onLeft { log.error { "error while load account by id: $it" } }


    private val ctx = Job() + CoroutineName(this::class.java.name) + Dispatchers.IO

    private companion object {
        private val log = KotlinLogging.logger { }
        private const val FIRST_VERSION = 1L
        private const val ID_FIELD = "id"
    }
}

