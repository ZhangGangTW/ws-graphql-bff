package com.workshop.graphql.bff.client.dto

import com.workshop.graphql.bff.type.Group

data class GroupDto(
    val id: String,
    val name: String
) {
    fun toGraphqlType(): Group {
        return Group(
            id = id,
            name = name
        )
    }
}