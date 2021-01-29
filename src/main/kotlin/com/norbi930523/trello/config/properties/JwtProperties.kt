package com.norbi930523.trello.config.properties

import com.auth0.jwt.algorithms.Algorithm.HMAC512
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Component
class JwtProperties(
        private val environment: Environment,

        @Value("\${auth.jwt.expiration-minutes}")
        private val expirationMinutes: Long
) {

    val expirationMillis
        get() = expirationMinutes * 60 * 1000

    val signingAlgorithm
        get() = HMAC512(environment.getRequiredProperty("JWT_SECRET"))

}
