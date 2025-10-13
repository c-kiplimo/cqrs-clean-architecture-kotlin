package com.collicode.cqrs_clean_architecture_kotlin.api.account.http.contracts

import com.collicode.common.exception.errors.*
import com.collicode.cqrs_clean_architecture_kotlin.api.account.http.controllerAdvice.ErrorHttpResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.Instant

internal fun mapErrorToResponse(err: AppError): ResponseEntity<*> {
    return when (err) {
        is GenericAppError -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            ErrorHttpResponse(
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message = err.msg,
                timestamp = Instant.now().toString()
            )
        )

        is AccountNotFoundError -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ErrorHttpResponse(
                status = HttpStatus.NOT_FOUND.value(),
                message = err.msg,
                timestamp = Instant.now().toString()
            )
        )

        is EmailValidationError -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorHttpResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                message = err.msg,
                timestamp = Instant.now().toString()
            )
        )

        is InvalidBalanceError -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorHttpResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                message = err.msg,
                timestamp = Instant.now().toString()
            )
        )

        is InvalidVersion -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorHttpResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                message = err.msg,
                timestamp = Instant.now().toString()
            )
        )

        is PaymentValidationError -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorHttpResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                message = err.msg,
                timestamp = Instant.now().toString()
            )
        )

        is LowerEventVersionError -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorHttpResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                message = err.toString(),
                timestamp = Instant.now().toString()
            )
        )

        is SameEventVersionError -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorHttpResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                message = err.toString(),
                timestamp = Instant.now().toString()
            )
        )

        is UpperEventVersionError -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorHttpResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                message = err.toString(),
                timestamp = Instant.now().toString()
            )
        )

        is InvalidTransactionError -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorHttpResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                message = err.msg,
                timestamp = Instant.now().toString()
            )
        )

        is InvalidBalanceAmount -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorHttpResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                message = err.msg,
                timestamp = Instant.now().toString()
            )
        )

        is InvalidBalanceCurrency -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorHttpResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                message = err.msg,
                timestamp = Instant.now().toString()
            )
        )

        is SerializationError -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorHttpResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                message = err.msg,
                timestamp = Instant.now().toString()
            )
        )
    }
}

internal fun <T : Any> okResponse(data: T) =
    ResponseEntity.status(HttpStatus.OK).body(BaseResponse(status = HttpStatus.OK, data = data))
