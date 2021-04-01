package com.workshop.graphql.bff.type

import com.expediagroup.graphql.server.extensions.getValueFromDataLoader
import com.workshop.graphql.bff.batch.GroupDataLoader
import graphql.schema.DataFetchingEnvironment
import java.util.concurrent.CompletableFuture

data class User(
    val id: String,
    val username: String,
    val email: String?,
    val role: Role,
    val group: Group,
) {
    fun group(environment: DataFetchingEnvironment): CompletableFuture<Group> {
        return environment.getValueFromDataLoader(GroupDataLoader::class.simpleName!!, group.id)
    }
}

enum class Role {
    USER,
    ADMIN,
}