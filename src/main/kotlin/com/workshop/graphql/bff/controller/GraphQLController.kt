package com.workshop.graphql.bff.controller

import com.workshop.graphql.bff.batch.GroupBatchLoader
import graphql.ExecutionInput
import graphql.GraphQL
import org.dataloader.DataLoader
import org.dataloader.DataLoaderRegistry
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@RestController
@RequestMapping
class GraphQLController(
    private val graphql: GraphQL,
    private val groupBatchLoader: GroupBatchLoader
) {
    @PostMapping("/graphql")
    fun execute(@RequestBody executionRequest: ExecutionRequest): Mono<Map<String, Any>> {
        val executeAsync = graphql.executeAsync(
            ExecutionInput.newExecutionInput()
                .query(executionRequest.query)
                .variables(executionRequest.variables)
                .dataLoaderRegistry(buildDataLoaderRegistry())
                .build()
        )
        return executeAsync.toMono()
            .map {
                it.toSpecification()
            }
    }

    fun buildDataLoaderRegistry(): DataLoaderRegistry {
        val registry = DataLoaderRegistry()
        registry.register(GroupBatchLoader::class.simpleName, DataLoader.newDataLoader(groupBatchLoader))
        return registry
    }
}