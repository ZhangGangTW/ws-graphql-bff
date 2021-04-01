package com.workshop.graphql.bff.batch

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.execution.KotlinDataLoader
import com.workshop.graphql.bff.client.GroupClient
import com.workshop.graphql.bff.type.Group
import org.dataloader.DataLoader
import org.springframework.stereotype.Component

@Component
class GroupDataLoader(
    private val groupClient: GroupClient
) : KotlinDataLoader<ID, Group> {

    override val dataLoaderName = GroupDataLoader::class.simpleName!!
    override fun getDataLoader() = DataLoader<ID, Group> { ids ->
        groupClient.findByIds(ids.map { it.value })
            .map { it.toGraphqlType() }
            .collectList()
            .toFuture()
    }
}