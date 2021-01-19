package com.norbi930523.trello

import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TrelloApplication

fun main(args: Array<String>) {
    runApplication<TrelloApplication>(*args) {
        webApplicationType = WebApplicationType.SERVLET
    }
}
