package com.norbi930523.trello.config

import com.norbi930523.trello.auth.AuthenticationFilter
import com.norbi930523.trello.auth.AuthenticationTokenProvider
import com.norbi930523.trello.common.filter.FilterChainExceptionHandler
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.logout.LogoutFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RegexRequestMatcher

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
        private val filterChainExceptionHandler: FilterChainExceptionHandler,
        private val authenticationTokenProvider: AuthenticationTokenProvider
) : WebSecurityConfigurerAdapter() {

    private val unsecuredRequests = listOf(
            AntPathRequestMatcher("/status/ready", HttpMethod.GET.name),
            AntPathRequestMatcher("/auth/login", HttpMethod.POST.name),
            RegexRequestMatcher("/users/?", HttpMethod.POST.name)
    )

    override fun configure(http: HttpSecurity?) {

        http!!.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.csrf().disable()

        // Needed to be able to access h2-console in the browser
        http.headers().frameOptions().sameOrigin()

        http.authorizeRequests()
                .requestMatchers(*unsecuredRequests.toTypedArray()).permitAll()
                .anyRequest().authenticated()

        http.addFilterBefore(filterChainExceptionHandler, LogoutFilter::class.java)
        http.addFilterBefore(
                AuthenticationFilter(unsecuredRequests, authenticationTokenProvider),
                UsernamePasswordAuthenticationFilter::class.java
        )
    }
}
