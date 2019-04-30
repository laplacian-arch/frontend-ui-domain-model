package laplacian_arch.client_app_arch.model


import laplacian.util.*

/**
 * page_action_example
 */
interface PageActionExample {
    /**
     * The title of this page_action_example.
     */
    val title: String
    /**
     * The given_params of this page_action_example.
     */
    val givenParams: String?
    /**
     * The given_state of this page_action_example.
     */
    val givenState: String?
    /**
     * The then of this page_action_example.
     */
    val then: String
    /**
     * action
     */
    val action: PageAction
}