package laplacian_arch.client_app_arch.model

import laplacian.metamodel.model.Entity


import laplacian.util.*

/**
 * view_model_item
 */
interface ViewModelItem {
    /**
     * The name of this view_model_item.
     */
    val name: String
    /**
     * The identifier of this view_model_item.
     */
    val identifier: String
    /**
     * The description of this view_model_item.
     */
    val description: String
    /**
     * define wether this item has multiple values or not
     */
    val multiple: Boolean
    /**
     * The name of the entity which is used as the type of this item. (This property can not be used with the type property.)

     */
    val entityName: String?
    /**
     * The type of this view_model_item.
     */
    val type: String
    /**
     * The default_value of this view_model_item.
     */
    val defaultValue: String?
    /**
     * The entity which is used as the type of this item. (This can be null if this item has a simple-type.)

     */
    val entity: Entity?
    /**
     * feature
     */
    val feature: Feature?
    /**
     * children
     */
    val children: List<ViewModelItem>
    /**
     * parent
     */
    val parent: ViewModelItem?
}