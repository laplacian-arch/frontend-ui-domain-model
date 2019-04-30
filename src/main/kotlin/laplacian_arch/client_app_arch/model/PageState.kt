package laplacian_arch.client_app_arch.model


import laplacian.util.*

/**
 * page_state
 */
interface PageState {
    /**
     * The name of this page_state.
     */
    val name: String
    /**
     * The type of this page_state.
     */
    val type: String
    /**
     * The expression of this page_state.
     */
    val expression: String
    /**
     * page
     */
    val page: Page
}