package com.norbi930523.trello.common.filter

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.servlet.HandlerExceptionResolver
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

// Based on https://stackoverflow.com/a/55864206
@Component
class FilterChainExceptionHandler(
        @Qualifier("handlerExceptionResolver")
        val exceptionResolver: HandlerExceptionResolver
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        try {
            chain.doFilter(request, response)
        } catch (e: RuntimeException) {
            exceptionResolver.resolveException(request, response, null, e)
        }
    }

}
