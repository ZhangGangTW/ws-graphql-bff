package com.workshop.graphql.bff.client

import com.workshop.graphql.bff.type.Group
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GroupClient {
    private val log: Logger = LoggerFactory.getLogger(GroupClient::class.java)

    fun findById(id: String): Mono<Group> {
        log.info("### find group by id: $id")
        return Mono.just(
            Group(
                id = id,
                name = "group$id"
            )
        )
    }
}