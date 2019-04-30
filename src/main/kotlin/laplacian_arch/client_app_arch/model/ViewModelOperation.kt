package laplacian_arch.client_app_arch.model

import laplacian.metamodel.model.NamedParam

import laplacian.metamodel.model.Entity


import laplacian.util.*

/**
 * view_model_operation
 */
interface ViewModelOperation {
    /**
     * The name of this view_model_operation.
     */
    val name: String
    /**
     * The description of this view_model_operation.
     */
    val description: String?
    /**
     * The return_type of this view_model_operation.
     */
    val returnType: String
    /**
     * Defines this view_model_operation is multiple or not.
     */
    val multiple: Boolean
    /**
     * The return_entity_name of this view_model_operation.
     */
    val returnEntityName: String?
    /**
     * The snippet of this view_model_operation.
     */
    val snippet: String
    /**
     * feature
     */
    val feature: Feature
    /**
     * params
     */
    val params: List<NamedParam>
    /**
     * entity
     */
    val entity: Entity?
}