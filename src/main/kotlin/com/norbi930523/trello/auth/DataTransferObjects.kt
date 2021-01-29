package com.norbi930523.trello.auth

data class AuthenticationToken(val token: String)

data class LoginRequest(val username: String?, val password: String?)
