package com.workshop.graphql.bff.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientsConfig {
    @Value("\${adapter.sidecar-egress}")
    private lateinit var sidecarEgress: String


    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
            .baseUrl(sidecarEgress)
            .build()
    }
}