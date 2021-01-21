package com.norbi930523.trello.user.service

import com.norbi930523.trello.user.api.CreateUserRequest
import com.norbi930523.trello.user.api.CreateUserResponse
import com.norbi930523.trello.user.model.User
import com.norbi930523.trello.user.model.UserRepository
import com.norbi930523.trello.user.model.UserView
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun createUser(userData: CreateUserRequest): CreateUserResponse {
        val user = User()
        user.username = userData.username
        user.password = userData.password

        val createdUser = userRepository.save(user)

        return CreateUserResponse(createdUser.id)
    }

    fun getUserById(id: Long): UserView {
        val user = userRepository
                .findById(id)
                .orElseThrow { RuntimeException("User not found by id: $id") }

        return UserView(user.id, user.username)
    }

}
