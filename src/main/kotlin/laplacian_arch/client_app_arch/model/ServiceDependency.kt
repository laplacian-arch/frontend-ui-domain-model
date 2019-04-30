package laplacian_arch.client_app_arch.model

import laplacian_arch.service_api_arch.model.Service


import laplacian.util.*

/**
 * service_dependency
 */
interface ServiceDependency {
    /**
     * The service_name of this service_dependency.
     */
    val serviceName: String
    /**
     * client
     */
    val client: Client
    /**
     * service
     */
    val service: Service
}