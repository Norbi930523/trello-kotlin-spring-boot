package com.norbi930523.trello.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class AuthConfig {

    @Value("\${auth.bcrypt.rounds}")
    val bcryptRounds: Int = 10

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder(bcryptRounds)

}
