package com.norbi930523.trello.common.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * API endpoints to check the status of the application, e.g. readiness, liveness.
 */
@RestController
@RequestMapping("/status")
class StatusRestController {

    /**
     * Check if the application is initialized and ready to accept connections.
     */
    @GetMapping("/ready")
    fun ready() = "OK"

}
