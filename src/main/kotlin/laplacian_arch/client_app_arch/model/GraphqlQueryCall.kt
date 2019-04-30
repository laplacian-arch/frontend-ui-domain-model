package laplacian_arch.client_app_arch.model

import laplacian_arch.service_api_arch.model.Service

import laplacian_arch.service_api_arch.model.GraphqlQuery


import laplacian.util.*

/**
 * graphql_query_call
 */
interface GraphqlQueryCall {
    /**
     * The service_name of this graphql_query_call.
     */
    val serviceName: String
    /**
     * The name of this graphql_query_call.
     */
    val name: String
    /**
     * action
     */
    val action: PageAction
    /**
     * service
     */
    val service: Service
    /**
     * graphql_query
     */
    val graphqlQuery: GraphqlQuery
        get() = service.graphqlQueries.find{ it.name == name }!!
}