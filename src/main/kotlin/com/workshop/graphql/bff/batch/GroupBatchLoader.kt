package com.workshop.graphql.bff.batch

import com.workshop.graphql.bff.client.GroupClient
import com.workshop.graphql.bff.type.Group
import org.dataloader.BatchLoader
import org.springframework.stereotype.Service
import java.util.concurrent.CompletionStage

@Service
class GroupBatchLoader(
    private val groupClient: GroupClient
) : BatchLoader<String, Group> {
    override fun load(keys: List<String>): CompletionStage<List<Group>> {
        return groupClient.findByIds(keys)
            .map { it.toGraphqlType() }
            .collectList()
            .toFuture()
    }

}