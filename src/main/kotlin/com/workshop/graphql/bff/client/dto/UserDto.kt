package com.workshop.graphql.bff.client.dto

import com.workshop.graphql.bff.type.Role
import com.workshop.graphql.bff.type.User

data class UserDto(
    val id: String,
    val username: String,
    val email: String?,
    val role: String,
    val groupId: String,
){
    fun toGraphqlType(): User {
        return User(
            id = id,
            username = username,
            role = Role.valueOf(role),
            email = email,
            groupId = groupId
        )
    }
}