package com.workshop.graphql.bff.fetcher

import com.workshop.graphql.bff.client.GroupClient
import com.workshop.graphql.bff.client.dto.GroupDto
import com.workshop.graphql.bff.type.Group
import com.workshop.graphql.bff.type.User
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Service
import java.util.concurrent.Future

@Service
class GroupDataFetcher(
    private val groupClient: GroupClient
) : DataFetcher<Future<Group>> {

    override fun get(environment: DataFetchingEnvironment): Future<Group> {
        val user = environment.getSource<User>()
        return groupClient.findById(user.group.id)
            .map { it.toGraphqlType() }
            .toFuture()
    }

    fun GroupDto.toGraphqlType(): Group {
        return Group(
            id = id,
            name = name
        )
    }

}