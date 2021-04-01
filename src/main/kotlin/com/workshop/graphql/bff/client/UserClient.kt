package com.workshop.graphql.bff.client

import com.workshop.graphql.bff.type.Role
import com.workshop.graphql.bff.type.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class UserClient {
    private val log: Logger = LoggerFactory.getLogger(UserClient::class.java)

    fun findById(id: String): Mono<User> {
        log.info("### find user by id: $id")
        return if (id == "1") {
            Mono.just(
                User(
                    id = id,
                    username = "user1",
                    email = null,
                    role = Role.ADMIN,
                    groupId = "g1"
                )
            )
        } else {
            Mono.error(IllegalStateException("not found"))
        }

    }
}