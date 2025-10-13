package com.collicode.cqrs_clean_architecture_kotlin.domain.common.utils

import kotlinx.coroutines.CancellationException


suspend inline fun <R> runSuspendCatching(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (c: CancellationException) {
        throw c
    } catch (e: Throwable) {
        Result.failure(e)
    }
}