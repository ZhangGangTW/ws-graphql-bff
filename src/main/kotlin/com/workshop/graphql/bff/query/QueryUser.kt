package com.workshop.graphql.bff.query

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Query
import com.workshop.graphql.bff.client.UserClient
import com.workshop.graphql.bff.exception.UserNotFoundException
import com.workshop.graphql.bff.type.User
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class QueryUser(
    private val userClient: UserClient,
    private val instanceId: String
) : Query {
    fun user(id: ID): CompletableFuture<User> {
        return userClient.findById(id.value)
            .map {
                val user = it.toGraphqlType()
                user.instanceId = instanceId
                user
            }
            .onErrorMap(IllegalStateException::class.java) { UserNotFoundException() }
            .toFuture()
    }

    fun users(): CompletableFuture<List<User>> {
        return userClient.findAll()
            .map {
                val user = it.toGraphqlType()
                user.instanceId = instanceId
                user
            }
            .collectList()
            .toFuture()
    }
}