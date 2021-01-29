package com.norbi930523.trello.auth

import com.norbi930523.trello.common.api.exception.ValidationException
import com.norbi930523.trello.common.validation.NotBlankValidator
import com.norbi930523.trello.user.model.UserRepository
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
        val authenticationTokenProvider: AuthenticationTokenProvider,
        val passwordEncoder: PasswordEncoder,
        val userRepository: UserRepository,
) {

    fun login(username: String?, rawPassword: String?): AuthenticationToken {
        listOfNotNull(
                NotBlankValidator.validate("username", username),
                NotBlankValidator.validate("password", rawPassword)
        ).also {
            if (it.isNotEmpty()) {
                throw ValidationException(it)
            }
        }

        return userRepository.findByUsername(username!!)?.let {
            if (passwordEncoder.matches(rawPassword, it.password)) {
                authenticationTokenProvider.createAuthenticationToken(username)
            } else {
                throw BadCredentialsException("Incorrect password for user: $username")
            }
        } ?: throw BadCredentialsException("User does not exist: $username")
    }

}
