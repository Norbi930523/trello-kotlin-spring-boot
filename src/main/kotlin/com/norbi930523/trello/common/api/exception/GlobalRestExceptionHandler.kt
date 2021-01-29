package com.norbi930523.trello.common.api.exception

import com.norbi930523.trello.common.validation.ValidationError
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletResponse

@RestControllerAdvice
class GlobalRestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException::class)
    fun validationError(e: ValidationException): ErrorResponse {
        return ValidationErrorResponse(e.message, e.errors)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyExistsException::class)
    fun alreadyExists(e: AlreadyExistsException): ErrorResponse {
        return ErrorResponse("ALREADY_EXISTS", e.message)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException::class)
    fun badCredentials(e: BadCredentialsException): ErrorResponse {
        return ErrorResponse("BAD_CREDENTIALS", e.message)
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException::class)
    fun authenticationError(
            e: AuthenticationException,
            response: HttpServletResponse
    ): ErrorResponse {

        return ErrorResponse("UNAUTHORIZED", e.message)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun notFound(e: NotFoundException): ErrorResponse {
        return ErrorResponse("NOT_FOUND", e.message)
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException::class)
    fun unknownError(e: RuntimeException): ErrorResponse {
        return ErrorResponse("UNEXPECTED_ERROR", e.message)
    }

}

open class ErrorResponse(val code: String, val message: String? = "Unknown Error")

class ValidationErrorResponse(
        message: String? = "Validation error",
        val errors: List<ValidationError>
) : ErrorResponse("VALIDATION_ERROR", message)
