package com.norbi930523.trello.common.api.exception

import com.norbi930523.trello.common.validation.ValidationError

class NotFoundException(override val message: String?) : RuntimeException(message)

class AlreadyExistsException(override val message: String?) : RuntimeException(message)

class AuthenticationException(override val message: String?) : RuntimeException(message)

class ValidationException(val errors: List<ValidationError>) : RuntimeException("Validation error")
