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
class UserDataFetcher(
    private val userClient: UserClient
) : DataFetcher<Future<User>> {

    override fun get(environment: DataFetchingEnvironment): Future<User> {
        val id = environment.getArgument<String>("id")
        return userClient.findById(id)
            .map { it.toGraphqlType() }
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