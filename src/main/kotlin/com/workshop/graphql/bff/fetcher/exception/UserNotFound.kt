package com.workshop.graphql.bff.fetcher.exception

import graphql.ErrorClassification
import graphql.ErrorType
import graphql.GraphQLError
import graphql.language.SourceLocation

class UserNotFound : GraphQLError {
    override fun getMessage(): String {
        return "User not found"
    }

    override fun getLocations(): List<SourceLocation> {
        return emptyList()
    }

    override fun getErrorType(): ErrorClassification {
        return ErrorType.DataFetchingException
    }

    override fun getExtensions(): Map<String, Any> {
        return mapOf("code" to 1001)
    }
}