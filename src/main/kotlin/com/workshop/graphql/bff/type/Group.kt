package com.workshop.graphql.bff.type

import com.expediagroup.graphql.generator.scalars.ID

data class Group(
    val id: ID,
    val name: String? = null
)