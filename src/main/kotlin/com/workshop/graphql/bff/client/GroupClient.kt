package com.workshop.graphql.bff.client

import com.workshop.graphql.bff.client.dto.GroupDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class GroupClient {
    private val log: Logger = LoggerFactory.getLogger(GroupClient::class.java)

    fun findById(id: String): Mono<GroupDto> {
        log.info("### find group by id: $id")
        return Mono.just(
            GroupDto(
                id = id,
                name = "group-$id"
            )
        )
    }

    fun findByIds(ids: List<String>): Flux<GroupDto> {
        log.info("### find group by ids: $ids")
        return Flux.fromIterable(
            ids.map {
                GroupDto(
                    id = it,
                    name = "group-$it"
                )
            }
        )
    }
}