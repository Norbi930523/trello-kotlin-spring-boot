package com.norbi930523.trello.common.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/status")
@Tag(name = "status", description = "API endpoints to check the status of the application, e.g. readiness, liveness.")
class StatusRestController {

    @Operation(description = "Check if the application is initialized and ready to accept connections.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "The application is ready.")
    ])
    @GetMapping("/ready")
    fun ready() = "OK"

}
