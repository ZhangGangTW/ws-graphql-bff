package com.workshop.graphql.bff.client

import com.workshop.graphql.bff.client.dto.UserDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class UserClient(
    private val userWebClient: WebClient
) {
    private val log: Logger = LoggerFactory.getLogger(UserClient::class.java)

    fun findById(id: String): Mono<UserDto> {
        log.info("### find user by id: $id")
        return userWebClient.get()
            .uri("/users/{id}", id)
            .retrieve()
            .bodyToMono(UserDto::class.java)
    }

    fun findAll(): Flux<UserDto> {
        log.info("### find all users")
        return Flux.fromIterable(
            listOf(
                UserDto(
                    id = "1",
                    username = "user1",
                    email = null,
                    role = "ADMIN",
                    groupId = "g1"
                ),
                UserDto(
                    id = "2",
                    username = "user2",
                    email = "user2@gmail.com",
                    role = "USER",
                    groupId = "g2"
                ),
                UserDto(
                    id = "3",
                    username = "user3",
                    email = "user3@gmail.com",
                    role = "USER",
                    groupId = "g3"
                )
            )
        )
    }
}