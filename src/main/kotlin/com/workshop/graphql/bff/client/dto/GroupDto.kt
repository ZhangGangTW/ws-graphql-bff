package com.workshop.graphql.bff.client.dto

import com.expediagroup.graphql.generator.scalars.ID
import com.workshop.graphql.bff.type.Group

data class GroupDto(
    val id: String,
    val name: String
) {
    fun toGraphqlType(): Group {
        return Group(
            id = ID(id),
            name = name
        )
    }
}