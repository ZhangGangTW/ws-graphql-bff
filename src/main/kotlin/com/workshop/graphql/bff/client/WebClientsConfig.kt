package com.workshop.graphql.bff.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientsConfig {
    @Value("\${adapter.api-key}")
    private lateinit var apiKey: String


    @Bean
    fun userWebClient(
        @Value("\${adapter.user-service-url}") userServiceHost: String
    ): WebClient {
        return WebClient.builder()
            .baseUrl(userServiceHost)
            .defaultHeader("api-key", apiKey)
            .build()
    }
}