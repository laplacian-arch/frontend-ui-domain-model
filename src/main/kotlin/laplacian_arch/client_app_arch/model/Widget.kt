package laplacian_arch.client_app_arch.model

import laplacian.metamodel.model.NamedParam


import laplacian.util.*

/**
 * A widget represents a type of a visible UI component.
 */
interface Widget {
    /**
     * The group of this widget.
     */
    val group: String
    /**
     * The name of this widget.
     */
    val name: String
    /**
     * The template string which defines appearance of this widget.

     */
    val template: String
    /**
     * The configurable parameters with whtch the appearance and behavior of this widget can be changed.

     */
    val params: List<NamedParam>
    /**
     * The events which can be used to hook certain actions.

     */
    val events: List<NamedParam>
}