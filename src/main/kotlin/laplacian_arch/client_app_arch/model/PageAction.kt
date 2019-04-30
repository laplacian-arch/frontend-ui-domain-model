package laplacian_arch.client_app_arch.model

import laplacian.metamodel.model.NamedParam


import laplacian.util.*

/**
 * An page action is the handler which is called when a certain event happens in this page.

 */
interface PageAction {
    /**
     * The name of this page_action.
     */
    val name: String
    /**
     * The identifier of this page_action.
     */
    val identifier: String
    /**
     * The description of this page_action.
     */
    val description: String
    /**
     * page
     */
    val page: Page
    /**
     * params
     */
    val params: List<NamedParam>
    /**
     * rest_api_call
     */
    val restApiCall: RestApiCall?
    /**
     * graphql_query_call
     */
    val graphqlQueryCall: GraphqlQueryCall?
    /**
     * examples
     */
    val examples: List<PageActionExample>
}