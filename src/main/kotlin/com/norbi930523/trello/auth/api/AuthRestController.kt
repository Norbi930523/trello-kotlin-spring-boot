package com.norbi930523.trello.auth.api

import com.norbi930523.trello.auth.AuthenticationService
import com.norbi930523.trello.auth.AuthenticationToken
import com.norbi930523.trello.auth.LoginRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthRestController(val authenticationService: AuthenticationService) {

    @PostMapping(value = ["/login"])
    fun login(@RequestBody loginRequest: LoginRequest): AuthenticationToken {
        return authenticationService.login(loginRequest.username, loginRequest.password)
    }

}
