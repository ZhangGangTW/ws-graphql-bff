package com.workshop.graphql.bff.client

import com.workshop.graphql.bff.type.Role
import com.workshop.graphql.bff.type.User
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class UserClient {
    fun findById(id: String): Mono<User> {
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