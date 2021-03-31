package com.workshop.graphql.bff.controller

import graphql.ExecutionInput
import graphql.GraphQL
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@RestController
@RequestMapping
class GraphQLController(
    private val graphql: GraphQL
) {
    @PostMapping("/graphql")
    fun execute(@RequestBody executionRequest: ExecutionRequest): Mono<Map<String, Any>> {
        val executeAsync = graphql.executeAsync(
            ExecutionInput.newExecutionInput()
                .query(executionRequest.query)
                .variables(executionRequest.variables)
                .build()
        )
        return executeAsync.toMono()
            .map {
                it.toSpecification()
            }
    }
}