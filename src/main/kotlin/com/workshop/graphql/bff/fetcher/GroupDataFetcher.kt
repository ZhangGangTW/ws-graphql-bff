package com.workshop.graphql.bff.fetcher

import com.workshop.graphql.bff.batch.GroupBatchLoader
import com.workshop.graphql.bff.type.Group
import com.workshop.graphql.bff.type.User
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Service
import java.util.concurrent.Future

@Service
class GroupDataFetcher : DataFetcher<Future<Group>> {
    override fun get(environment: DataFetchingEnvironment): Future<Group> {
        val user = environment.getSource<User>()
        val dataLoader = environment.getDataLoader<String, Group>(GroupBatchLoader::class.simpleName)
        return dataLoader.load(user.group.id)
    }
}