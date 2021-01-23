package com.norbi930523.trello.user.service

import com.norbi930523.trello.common.api.exception.NotFoundException
import com.norbi930523.trello.common.api.exception.ValidationException
import com.norbi930523.trello.common.validation.NotBlankValidator
import com.norbi930523.trello.common.validation.PasswordValidator
import com.norbi930523.trello.common.validation.ValidationError
import com.norbi930523.trello.user.api.CreateUserRequest
import com.norbi930523.trello.user.api.CreateUserResponse
import com.norbi930523.trello.user.model.User
import com.norbi930523.trello.user.model.UserRepository
import com.norbi930523.trello.user.model.UserView
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository, val passwordEncoder: PasswordEncoder) {

    fun createUser(userData: CreateUserRequest): CreateUserResponse {
        validateCreateUserRequest(userData).also {
            if (it.isNotEmpty()) {
                throw ValidationException(it)
            }
        }

        val user = User().apply {
            username = userData.username!!
            password = passwordEncoder.encode(userData.password!!)
        }

        val createdUser = userRepository.save(user)

        return CreateUserResponse(createdUser.id)
    }

    private fun validateCreateUserRequest(userData: CreateUserRequest): List<ValidationError> {
        return listOfNotNull(
                NotBlankValidator.validate("username", userData.username),
                PasswordValidator.validate("password", userData.password)
        )
    }

    fun getUserById(id: Long): UserView {
        val user = userRepository
                .findById(id)
                .orElseThrow { NotFoundException("User not found by id: $id") }

        return UserView(user.id, user.username)
    }

}
