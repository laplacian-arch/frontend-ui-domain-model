package laplacian_arch.client_app_arch.model

import laplacian.metamodel.model.NamedValue


import laplacian.util.*

/**
 * A page of this application.

 */
interface Page {
    /**
     * The name of this page.
     */
    val name: String
    /**
     * The feature_name of this page.
     */
    val featureName: String
    /**
     * The identifier of this page.
     */
    val identifier: String
    /**
     * The path of this page.
     */
    val path: String?
    /**
     * Whether this page is the default entry point of the feature.

     */
    val initial: Boolean
    /**
     * Whether this page is shown in a modal window.

     */
    val modal: Boolean
    /**
     * The condition which must be satisfied before rendering the content of this page.

     */
    val renderPageWhen: String?
    /**
     * The feature containing this page.

     */
    val feature: Feature
    /**
     * The list of UI items this page consists of.

     */
    val items: List<PageItem>
    /**
     * The list of handlers which is called when a certain event in this page happens.

     */
    val actions: List<PageAction>
    /**
     * A subset of the client side application state that relevant to the function of this page.

     */
    val states: List<PageState>
    /**
     * The list of event which may happen in this page.

     */
    val events: List<NamedValue>
}