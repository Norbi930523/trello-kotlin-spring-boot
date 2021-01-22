package com.norbi930523.trello.common.api.exception

import com.norbi930523.trello.common.validation.ValidationError
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalRestExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = [NotFoundException::class])
    fun notFound(e: NotFoundException): ErrorResponse {
        return ErrorResponse("NOT_FOUND", e.message)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [ValidationException::class])
    fun validationError(e: ValidationException): ErrorResponse {
        return ValidationErrorResponse(e.message, e.errors)
    }

}

open class ErrorResponse(val code: String, val message: String? = "Unknown Error")

class ValidationErrorResponse(
        message: String? = "Validation error",
        val errors: List<ValidationError>
) : ErrorResponse("VALIDATION_ERROR", message)
