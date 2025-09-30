package com.collicode.cqrs_clean_architecture_kotlin.application.account.services

import arrow.core.Either
import com.collicode.common.exception.errors.AppError
import com.collicode.common.scope.eitherScope
import com.collicode.cqrs_clean_architecture_kotlin.application.account.models.AccountsList
import com.collicode.cqrs_clean_architecture_kotlin.application.account.persistance.AccountProjectionRepository
import com.collicode.cqrs_clean_architecture_kotlin.application.account.persistance.AccountRepository
import com.collicode.cqrs_clean_architecture_kotlin.application.account.queries.GetAccountByEmailQuery
import com.collicode.cqrs_clean_architecture_kotlin.application.account.queries.GetAccountByIdQuery
import com.collicode.cqrs_clean_architecture_kotlin.application.account.queries.GetAllAccountsQuery
import com.collicode.cqrs_clean_architecture_kotlin.domain.account.Account
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.springframework.stereotype.Service


@Service
class AccountQueryServiceImpl(
    private val accountRepository: AccountRepository,
    private val accountProjectionRepository: AccountProjectionRepository
) : AccountQueryService {

    override suspend fun handle(query: GetAccountByIdQuery): Either<AppError, Account> = eitherScope(ctx) {
        accountRepository.getById(query.id).bind()
    }

    override suspend fun handle(query: GetAccountByEmailQuery): Either<AppError, Account> = eitherScope(ctx) {
        accountProjectionRepository.getByEmail(query.email).bind()
    }

    override suspend fun handle(query: GetAllAccountsQuery): Either<AppError, AccountsList> = eitherScope(ctx) {
        accountProjectionRepository.getAll(page = query.page, size = query.size).bind()
    }

    private val ctx = Job() + CoroutineName(this::class.java.name) + Dispatchers.IO
}