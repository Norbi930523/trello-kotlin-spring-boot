package com.norbi930523.trello.auth

import com.norbi930523.trello.common.api.exception.AuthenticationException
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(
        private val unsecuredRequests: List<RequestMatcher>,
        private val authenticationTokenProvider: AuthenticationTokenProvider
) : OncePerRequestFilter() {

    private val headerPrefix = "Bearer "

    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            chain: FilterChain
    ) {

        if (isSecuredRequest(request)) {
            val authToken = getTokenFromRequest(request) ?:
                throw AuthenticationException("Missing authentication token")

            if (!isTokenValid(authToken)) {
                throw AuthenticationException("Invalid authentication token: $authToken")
            }

            SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(
                    "username", authToken, listOf(SimpleGrantedAuthority("USER")))
        }

        chain.doFilter(request, response)
    }

    private fun getTokenFromRequest(request: HttpServletRequest): String? {
        val authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION)

        return authorizationHeader?.removePrefix(headerPrefix)
    }

    private fun isSecuredRequest(request: HttpServletRequest): Boolean {
        return unsecuredRequests.none { it.matches(request) }
    }

    private fun isTokenValid(token: String): Boolean {
        return authenticationTokenProvider.validateAuthenticationToken(token)
    }

}
