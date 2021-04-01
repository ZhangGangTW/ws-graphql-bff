package com.workshop.graphql.bff.type

data class User(
    val id: String,
    val username: String,
    val email: String?,
    val role: Role,
    val group: Group,
)

enum class Role {
    USER,
    ADMIN,
}