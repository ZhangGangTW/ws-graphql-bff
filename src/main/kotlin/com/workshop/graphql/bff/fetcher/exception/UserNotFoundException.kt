package com.workshop.graphql.bff.fetcher.exception

import com.workshop.graphql.bff.GraphQLException

class UserNotFoundException : GraphQLException() {
    override fun getExtensions(): Map<String, Any> {
        return mapOf(
            "code" to 1001,
            "message" to "User not found"
        )
    }
}