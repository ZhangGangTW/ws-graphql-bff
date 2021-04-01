package com.workshop.graphql.bff.client

import com.workshop.graphql.bff.client.dto.UserDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class UserClient {
    private val log: Logger = LoggerFactory.getLogger(UserClient::class.java)

    fun findById(id: String): Mono<UserDto> {
        log.info("### find user by id: $id")
        return if (id == "1") {
            Mono.just(
                UserDto(
                    id = id,
                    username = "user1",
                    email = null,
                    role = "ADMIN",
                    groupId = "g1"
                )
            )
        } else {
            Mono.error(IllegalStateException("not found"))
        }

    }
}