package com.workshop.graphql.bff.fetcher

import com.workshop.graphql.bff.client.UserClient
import com.workshop.graphql.bff.client.dto.UserDto
import com.workshop.graphql.bff.type.Group
import com.workshop.graphql.bff.type.Role
import com.workshop.graphql.bff.type.User
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Service
import java.util.concurrent.Future

@Service
class UsersDataFetcher(
    private val userClient: UserClient
) : DataFetcher<Future<List<User>>> {

    override fun get(environment: DataFetchingEnvironment): Future<List<User>> {
        return userClient.findAll()
            .map { it.toGraphqlType() }
            .collectList()
            .toFuture()
    }

    fun UserDto.toGraphqlType(): User {
        return User(
            id = id,
            username = username,
            role = Role.valueOf(role),
            email = email,
            group = Group(groupId)
        )
    }

}