package com.norbi930523.trello.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.exceptions.JWTVerificationException
import com.norbi930523.trello.config.properties.JwtProperties
import org.springframework.stereotype.Component
import java.util.*


@Component
class AuthenticationTokenProvider(private val jwtProperties: JwtProperties) {

    fun createAuthenticationToken(username: String): AuthenticationToken {
        val token = JWT.create()
                .withSubject(username)
                .withExpiresAt(Date(System.currentTimeMillis() + jwtProperties.expirationMillis))
                .sign(jwtProperties.signingAlgorithm)

        return AuthenticationToken(token)
    }

    fun validateAuthenticationToken(token: String): Boolean {
        return try {
            JWT.require(jwtProperties.signingAlgorithm).build().verify(token)
            true
        } catch (e: JWTVerificationException) {
            false
        }
    }

}
