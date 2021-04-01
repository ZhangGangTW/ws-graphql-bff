package com.workshop.graphql.bff.config

import com.workshop.graphql.bff.fetcher.GroupDataFetcher
import com.workshop.graphql.bff.fetcher.UserDataFetcher
import graphql.GraphQL
import graphql.execution.AsyncExecutionStrategy
import graphql.execution.AsyncSerialExecutionStrategy
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource

@Configuration
class GraphQLConfig {

    @Value("\${classpath:schema.graphql}")
    private lateinit var schema: Resource

    @Autowired
    private lateinit var userDataFetcher: UserDataFetcher

    @Autowired
    private lateinit var groupDataFetcher: GroupDataFetcher

    @Bean
    fun graphql(): GraphQL {
        //1. Parse schema
        val schemaParser = SchemaParser()
        val typeDefinitionRegistry = schemaParser.parse(schema.inputStream)
        //2. Add Data fetcher
        val runtimeWiring = RuntimeWiring.newRuntimeWiring()
            .type("Query") {
                it.dataFetcher("user", userDataFetcher)
            }
            .type("User") {
                it.dataFetcher("group", groupDataFetcher)
            }
            .build()
        //3. Build GraphQL instance
        val schema = SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring)
        return GraphQL.newGraphQL(schema)
            .queryExecutionStrategy(AsyncExecutionStrategy())//As default
            .mutationExecutionStrategy(AsyncSerialExecutionStrategy())//As default
            .build()
    }

}