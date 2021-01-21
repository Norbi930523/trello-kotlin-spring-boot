package com.norbi930523.trello.user.api

import com.norbi930523.trello.user.model.UserView
import com.norbi930523.trello.user.service.UserService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserRestController(val userService: UserService) {

    @PostMapping(
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun create(@RequestBody request: CreateUserRequest): CreateUserResponse {
        return userService.createUser(request)
    }

    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getById(@PathVariable id: Long): UserView {
        return userService.getUserById(id)
    }

}
