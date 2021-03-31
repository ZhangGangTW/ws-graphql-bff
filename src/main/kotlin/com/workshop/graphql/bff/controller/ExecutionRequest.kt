package com.workshop.graphql.bff.controller

data class ExecutionRequest(
    val query: String,
    val variables: Map<String, Any>? = mapOf()
)