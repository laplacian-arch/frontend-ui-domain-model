package laplacian_arch.client_app_arch.model

import laplacian_arch.service_api_arch.model.Service

import laplacian_arch.service_api_arch.model.RestResource

import laplacian_arch.service_api_arch.model.RestOperation


import laplacian.util.*

/**
 * rest_api_call
 */
interface RestApiCall {
    /**
     * The service_name of this rest_api_call.
     */
    val serviceName: String
    /**
     * The resource_name of this rest_api_call.
     */
    val resourceName: String
    /**
     * The http_method of this rest_api_call.
     */
    val httpMethod: String
    /**
     * The path of this rest_api_call.
     */
    val path: String
    /**
     * action
     */
    val action: PageAction
    /**
     * service
     */
    val service: Service
    /**
     * resource
     */
    val resource: RestResource
        get() = service.resources.find{ it.name == resourceName }!!
    /**
     * operation
     */
    val operation: RestOperation
        get() = resource.operations.find {
            it.method == httpMethod &&
            it.path == path
        }!!
}