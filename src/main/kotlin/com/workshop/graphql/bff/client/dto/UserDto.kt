package com.workshop.graphql.bff.client.dto

data class UserDto(
    val id: String,
    val username: String,
    val email: String?,
    val role: String,
    val groupId: String,
)