package laplacian_arch.client_app_arch.model

import laplacian.metamodel.model.Entity


import laplacian.util.*

/**
 * A feature of this application client.

 */
interface Feature {
    /**
     * The name of this feature.
     */
    val name: String
    /**
     * The identifier of this feature.
     */
    val identifier: String
    /**
     * The base_path of this feature.
     */
    val basePath: String
    /**
     * Whether this feature is the entry point of this application.

     */
    val initial: Boolean
    /**
     * The list of pages the feature contains.

     */
    val pages: List<Page>
    /**
     * The data structure of the client side application state concerning thie feature.

     */
    val state: List<ViewModelItem>
    /**
     * The queries to the client side application state.

     */
    val stateQueries: List<ViewModelOperation>
    /**
     * The list of functions which mutate the current client side application state.

     */
    val stateMutation: List<ViewModelOperation>
    /**
     * The list of entities this feature refers to

     */
    val relatingEntities: List<Entity>
        get() = (state.map{ it.entity } + stateQueries.map{ it.entity })
        .filterNotNull()
        .distinctBy{ it.fqn }
}