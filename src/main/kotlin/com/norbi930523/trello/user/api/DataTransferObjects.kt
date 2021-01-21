package com.norbi930523.trello.user.api

data class CreateUserRequest(val username: String, val password: String)

data class CreateUserResponse(val id: Long)
